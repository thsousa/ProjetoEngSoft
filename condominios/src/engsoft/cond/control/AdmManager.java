package engsoft.cond.control;

import engsoft.cond.screen.AdmScreen;
import engsoft.cond.screen.CondCadScreen;

public class AdmManager {

    private static AdmManager autoRef;


    private AdmScreen admScreen;
    private CondCadScreen condCadScreen;


    public AdmManager() {

    }

    public static AdmManager getInstance() {
        if (autoRef == null) {
            autoRef = new AdmManager();
        }

        return autoRef;
    }


    public AdmScreen getAdmScreen() {
        if (admScreen == null) {
            admScreen = new AdmScreen();
        }
        return admScreen;
    }    
    
    public CondCadScreen getCondCadScreen() {
        if (condCadScreen == null) {
            condCadScreen = new CondCadScreen();
        }
        return condCadScreen;
    }    

}
