package engsoft.cond.control;

import engsoft.cond.model.Usuario;
import engsoft.cond.screen.SignupScreen;

public class SignupManager {

    private static SignupManager autoRef;


    private SignupScreen signupScreen;


    public SignupManager() {

    }

    public static SignupManager getInstance() {
        if (autoRef == null) {
            autoRef = new SignupManager();
        }

        return autoRef;
    }


    public SignupScreen getSignupScreen() {
        if (signupScreen == null) {
            signupScreen = new SignupScreen();
        }
        return signupScreen;
    }

    public SignupScreen getSignupScreen(String email, String nome) {
        signupScreen = new SignupScreen(email, nome);
        return signupScreen;
    }


    public SignupScreen getSignupScreen(Usuario user) {
        signupScreen = new SignupScreen(user);
        return signupScreen;
    }

    /**
     * TODO: Validação de dados, tratamento de erro de cadastro (se quebrar unique no CPF/EMAIL/CNPJ)
     * @param nome
     * @param email
     * @param cpf
     * @param cnpj
     * @param tel1
     * @param tel2
     */
    public void doSignup(String nome, String email, String cpf, String cnpj, String tel1, String tel2) {
        Usuario nUser = new Usuario(email, nome, cpf, cnpj, tel1, tel2, "m");
        DatabaseManager.getInstance().saveUsuario(nUser);
        Usuario registeredUser = DatabaseManager.getInstance().getRegisteredUser(email);
        MainManager.getInstance().setActiveUser(registeredUser);
        MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu());
    }

}
