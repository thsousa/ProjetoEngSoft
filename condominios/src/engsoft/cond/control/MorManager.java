package engsoft.cond.control;

import engsoft.cond.screen.AdmScreen;
import engsoft.cond.screen.MorScreen;

public class MorManager {

    private static MorManager autoRef;


    private AdmScreen admScreen;
    private MorScreen morScreen;


    public MorManager() {

    }

    public static MorManager getInstance() {
        if (autoRef == null) {
            autoRef = new MorManager();
        }

        return autoRef;
    }


    public AdmScreen getAdmScreen() {
        if (admScreen == null) {
            admScreen = new AdmScreen();
        }
        return admScreen;
    }    
    
    public MorScreen getMorScreen() {
        if (morScreen == null) {
            morScreen = new MorScreen();
        }
        return morScreen;
    }    

}
