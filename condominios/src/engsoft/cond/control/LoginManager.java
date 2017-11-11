package engsoft.cond.control;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;

import engsoft.cond.model.Usuario;
import engsoft.cond.screen.TwitterLoginScreen;
import engsoft.cond.screen.GoogleLoginScreen;
import engsoft.cond.screen.LoginScreen;

public class LoginManager {

    private static LoginManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(LoginManager.class);

    private static final String GOOGLE_PROFILE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";
    public static final int GOOGLE_SUCCESS_CODE = 200;
    
    private static final String TWITTER_PROFILE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
    public static final int TWITTER_SUCCESS_CODE = 200;

    private LoginScreen loginScreen;
    private GoogleLoginScreen googleLoginScreen;
    private TwitterLoginScreen twitterLoginScreen;

    private final String secret = "secret" + new Random().nextInt(999_999);
    private final OAuth20Service googleService = new ServiceBuilder("978895109801-f59lh4uh36liom0qpbmvrqunlihe9lb9.apps.googleusercontent.com")
            .apiSecret("qpfr1NYqbzQdpCytYgefhsUp")
            .scope("profile email")
            .state(secret)
            .build(GoogleApi20.instance());
    
    final OAuth10aService twitterService = new ServiceBuilder("GzlBxSYEfYBNMJbQ5HNJdr8sf")
            .apiSecret("oNTN2YgB2HT59k1so4dKOF23XfneSSVFLUfessKgpKAyzUGAGj")
            .build(TwitterApi.instance());
    

    private OAuth2AccessToken googleAccessToken;
    private OAuth1AccessToken twitterAccessToken;
    private OAuth1RequestToken twitterRequestToken;


    public LoginManager() {

    }

    public static LoginManager getInstance() {
        if (autoRef == null) {
            autoRef = new LoginManager();
        }

        return autoRef;
    }


    public LoginScreen getLoginScreen() {        
        loginScreen = new LoginScreen();
        return loginScreen;
    }

    public void doGoogleLogin() {

        if (googleLoginScreen == null) {
            googleLoginScreen = new GoogleLoginScreen();
        }

    	if (MainManager.DEBUG) {
    		LOGGER.info("DEBUG - Pulando login pelo Google, insira o e-mail na próxima tela");
            googleLoginScreen.addError("MODO DEBUG", "Insira e-mail do usuário que deseja logar.");
            MainManager.getInstance().changeScreen(googleLoginScreen);
            return;
    	}

        LOGGER.info("Iniciando login pelo Google");
        Map<String, String> params = new HashMap<>();
        params.put("access_type",  "offline");
        params.put("prompt", "consent");
        String authUrl = googleService.getAuthorizationUrl(params);

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(authUrl));
                MainManager.getInstance().changeScreen(googleLoginScreen);
            } catch (IOException e) {
                googleLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
                LOGGER.error("Erro ao abrir navegador - IOException");
            } catch (URISyntaxException e) {
                googleLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
                LOGGER.error("Erro ao abrir navegador - URL syntax");
            }
        } else {
            googleLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
            LOGGER.error("Erro ao abrir navegador - Desktop unsupported");
        }

    }

    public void doGoogleLogin(String authCode) {

    	if (MainManager.DEBUG) {
    		LOGGER.info("Fazendo login para o usuário " + authCode);
    		Usuario regUser = DatabaseManager.getInstance().getRegisteredUser(authCode);

            if (regUser == null) {
                MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(authCode, "--"));
            } else {
                MainManager.getInstance().setActiveUser(regUser);
                MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu());
            }
            return;
    	}

        try {
            googleAccessToken = googleService.getAccessToken(authCode);
        } catch (IOException e) {
            LOGGER.error("Erro ao obter token google - IOException");
            googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (InterruptedException e) {
            LOGGER.error("Erro ao obter token google - Interrupted");
            googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (ExecutionException e) {
            LOGGER.error("Erro ao obter token google - Execution");
            googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (Exception e) {
            LOGGER.error("Erro ao obter token google - Generic");
            e.printStackTrace();
            googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        }

        if (googleAccessToken != null) {

            OAuthRequest req = new OAuthRequest(Verb.GET, GOOGLE_PROFILE_URL);
            googleService.signRequest(googleAccessToken, req);
            try {
                Response res = googleService.execute(req);

                if (res.getCode() == GOOGLE_SUCCESS_CODE) {

                    JSONObject obj = new JSONObject(res.getBody());

                    String nome = obj.getString("name");
                    String email = obj.getString("email");

                    Usuario regUser = DatabaseManager.getInstance().getRegisteredUser(email);

                    if (regUser == null) {
                        MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(email, nome));
                    } else {
                        MainManager.getInstance().setActiveUser(regUser);
                        MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu());
                    }

                } else {
                    LOGGER.error("Erro ao obter dados google - código " + res.getCode());
                    googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
                }


            } catch (IOException e) {
                LOGGER.error("Erro ao obter token google - IOException");
                googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            } catch (InterruptedException e) {
                LOGGER.error("Erro ao obter token google - Interrupted");
                googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            } catch (ExecutionException e) {
                LOGGER.error("Erro ao obter token google - Execution");
                googleLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            }

        }

    }
    
    public void doTwitterLogin() {

        if (twitterLoginScreen == null) {
             twitterLoginScreen = new TwitterLoginScreen();
        }

        if (MainManager.DEBUG) {
                LOGGER.info("DEBUG - Pulando login pelo Twitter, insira o e-mail na próxima tela");
            twitterLoginScreen.addError("MODO DEBUG", "Insira e-mail do usuário que deseja logar.");
            MainManager.getInstance().changeScreen(twitterLoginScreen);
            return;
        }

        LOGGER.info("Iniciando login pelo Twitter");        
        
        if (Desktop.isDesktopSupported()) {
            try {                
                twitterRequestToken = twitterService.getRequestToken();                
                Desktop.getDesktop().browse(new URI(twitterService.getAuthorizationUrl(twitterRequestToken)));
                MainManager.getInstance().changeScreen(twitterLoginScreen);
            } catch (IOException e) {
                twitterLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
                LOGGER.error("Erro ao abrir navegador - IOException");
            } catch (URISyntaxException e) {
                twitterLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
                LOGGER.error("Erro ao abrir navegador - URL syntax");
            } catch (InterruptedException | ExecutionException e) {
                twitterLoginScreen.addError("Login Falhou", "Não foi possível conectar ao Twitter.");
                LOGGER.error("Erro ao obter request token - Twitter");
            }
        } else {
            twitterLoginScreen.addError("Navegador indisponível", "Não foi possível abrir o navegador.");
            LOGGER.error("Erro ao abrir navegador - Desktop unsupported");
        }

    }

    public void doTwitterLogin(String authCode) {

        if (MainManager.DEBUG) {
                LOGGER.info("Fazendo login para o usuário " + authCode);
                Usuario regUser = DatabaseManager.getInstance().getRegisteredUser(authCode);

            if (regUser == null) {
                MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(authCode, "--"));
            } else {
                MainManager.getInstance().setActiveUser(regUser);
                MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu());
            }
            return;
        }

        try {
            twitterAccessToken = twitterService.getAccessToken(twitterRequestToken, authCode);
        } catch (IOException e) {
            LOGGER.error("Erro ao obter token twitter - IOException");
            twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (InterruptedException e) {
            LOGGER.error("Erro ao obter token twitter - Interrupted");
            twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (ExecutionException e) {
            LOGGER.error("Erro ao obter token twitter - Execution");
            twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        } catch (Exception e) {
            LOGGER.error("Erro ao obter token twitter - Generic");
            e.printStackTrace();
            twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
        }

        if (twitterAccessToken != null) {

            OAuthRequest req = new OAuthRequest(Verb.GET, TWITTER_PROFILE_URL);
            req.addParameter("include_email", "true");
            twitterService.signRequest(twitterAccessToken, req);
            try {
                Response res = twitterService.execute(req);
                
                if (res.getCode() == TWITTER_SUCCESS_CODE) {

                    JSONObject obj = new JSONObject(res.getBody());

                    String name = obj.getString("name");
                    String email = obj.getString("email");

                    Usuario regUser = DatabaseManager.getInstance().getRegisteredUser(email);

                    if (regUser == null) {
                        MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(email, name));
                    } else {
                        MainManager.getInstance().setActiveUser(regUser);
                        MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu());
                    }

                } else {
                    LOGGER.error("Erro ao obter dados twitter - código " + res.getCode());
                    twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
                }


            } catch (IOException e) {
                LOGGER.error("Erro ao obter token twitter - IOException");
                twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            } catch (InterruptedException e) {
                LOGGER.error("Erro ao obter token twitter - Interrupted");
                twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            } catch (ExecutionException e) {
                LOGGER.error("Erro ao obter token twitter - Execution");
                twitterLoginScreen.addError("Autenticação", "Não foi possível fazer login.");
            }

        }

    }

}
