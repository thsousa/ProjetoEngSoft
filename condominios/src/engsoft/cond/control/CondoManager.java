package engsoft.cond.control;

import java.util.ArrayList;

import engsoft.cond.model.AreaComum;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;
import engsoft.cond.screen.CondoScreen;
import static engsoft.cond.util.Constants.*;

public class CondoManager {

    private static CondoManager autoRef;


    private CondoScreen condoScreen;


    public CondoManager() {

    }

    public static CondoManager getInstance() {
        if (autoRef == null) {
            autoRef = new CondoManager();
        }

        return autoRef;
    }


    public CondoScreen getCondoScreen() {
        condoScreen = new CondoScreen();
        repopScreenLists();
        return condoScreen;
    }
    
    public CondoScreen getCondoScreen(boolean remake) {
        if (!remake) {
            return condoScreen;
        }
        condoScreen = new CondoScreen();
        repopScreenLists();
        return condoScreen;        
    }
    
    public void repopScreenLists() {
        condoScreen.populateBuildingsPanel(new ArrayList<Condominio>(MainManager.getInstance().getActiveUser().getCondominios()));        
    }

    public void handleBldgSelection(Condominio selectedValue) {
        
        condoScreen.populateAreasPanel(new ArrayList<AreaComum>(selectedValue.getAreas()));
        ArrayList<Object> usuarios = DatabaseManager.getInstance().getListFromTable("Usuario");
        ArrayList<Usuario> sinds_condominio = new ArrayList<>();
        for (Object u : usuarios) {
            Usuario user = (Usuario) u;
            if (user.getNivel_acesso().contains(SIND_LEVEL) && user.getCondominios().contains(selectedValue)) {
                sinds_condominio.add(user);
            }
        }
        condoScreen.populateSindsPanel(sinds_condominio);
        
    }

    public void handleSindAccess(Condominio selectedValue) {
        if (selectedValue != null) {
            Usuario currUser = MainManager.getInstance().getActiveUser();
            Usuario tmpUser = new Usuario();
            tmpUser.setNome(currUser.getNome());
            tmpUser.setEmail(currUser.getEmail());
            tmpUser.setNivel_acesso(SIND_LEVEL);
            tmpUser.addCondominio(selectedValue);
            MainManager.getInstance().swapTempUser(tmpUser);
            MainManager.getInstance().changeScreen(MenuManager.getInstance().getMainMenu(true));
        }
    }


}
