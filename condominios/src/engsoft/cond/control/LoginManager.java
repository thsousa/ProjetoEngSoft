package engsoft.cond.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import engsoft.cond.screen.GoogleLoginScreen;
import engsoft.cond.screen.LoginScreen;

public class LoginManager {   
    
    private static LoginManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(LoginManager.class);
    
    private LoginScreen loginScreen;
    private GoogleLoginScreen googleLoginScreen;
    
    
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
        
        LOGGER.info("Iniciando login pelo Google");
        
               
        MainManager.getInstance().changeScreen(googleLoginScreen);
    }
    
    public void doGoogleLogin(String authCode) {
        
        System.out.println("hello");
        
    }

}
