package engsoft.cond.control;

import engsoft.cond.screen.CondoScreen;

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
        if (condoScreen == null) {
            condoScreen = new CondoScreen();
        }
        return condoScreen;
    }


}
