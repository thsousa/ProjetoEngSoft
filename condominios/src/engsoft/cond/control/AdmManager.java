package engsoft.cond.control;

import java.util.ArrayList;

import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;
import static engsoft.cond.util.Constants.*;
import engsoft.cond.screen.AdmScreen;

public class AdmManager {

    private static AdmManager autoRef;


    private AdmScreen admScreen;

    public AdmManager() {

    }

    public static AdmManager getInstance() {
        if (autoRef == null) {
            autoRef = new AdmManager();
        }

        return autoRef;
    }


    public AdmScreen getAdmScreen() {
        admScreen = new AdmScreen();
        repopScreenLists();
        return admScreen;
    }   
    
    
    public void repopScreenLists() {
        ArrayList<Object> condominios = DatabaseManager.getInstance().getListFromTable("Condominio");
        admScreen.populateBuildingsPanel(condominios);
    }
    
    public void handleBldgSelection(Condominio selected) {
        ArrayList<Object> usuarios = DatabaseManager.getInstance().getListFromTable("Usuario");
        ArrayList<Usuario> adms_condominio = new ArrayList<>();
        for (Object u : usuarios) {
            Usuario user = (Usuario) u;
            if (user.getNivel_acesso().contains(ADMIN_LEVEL) && user.getCondominios().contains(selected)) {
                adms_condominio.add(user);
            }
        }
        admScreen.populateAdmsPanel(adms_condominio);
    }

}
