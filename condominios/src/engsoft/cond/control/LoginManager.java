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

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import engsoft.cond.model.Usuario;
import engsoft.cond.screen.GoogleLoginScreen;
import engsoft.cond.screen.LoginScreen;

public class LoginManager {

    private static LoginManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(LoginManager.class);

    private static final String GOOGLE_PROFILE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";
    public static final int GOOGLE_SUCCESS_CODE = 200;

    private LoginScreen loginScreen;
    private GoogleLoginScreen googleLoginScreen;

    private final String secret = "secret" + new Random().nextInt(999_999);
    private final OAuth20Service googleService = new ServiceBuilder("978895109801-f59lh4uh36liom0qpbmvrqunlihe9lb9.apps.googleusercontent.com")
            .apiSecret("qpfr1NYqbzQdpCytYgefhsUp")
            .scope("profile email")
            .state(secret)
            .build(GoogleApi20.instance());

    private OAuth2AccessToken googleAccessToken;


    public LoginManager() {

    }

    public static LoginManager getInstance() {
        if (autoRef == null) {
            autoRef = new LoginManager();
        }

        return autoRef;
    }


    public LoginScreen getLoginScreen() {
        if (loginScreen == null) {
            loginScreen = new LoginScreen();
        }
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

}
