package engsoft.cond.control;

import engsoft.cond.screen.NoticesScreen;

public class NoticesManager {

    private static NoticesManager autoRef;


    private NoticesScreen noticesScreen;


    public NoticesManager() {

    }

    public static NoticesManager getInstance() {
        if (autoRef == null) {
            autoRef = new NoticesManager();
        }

        return autoRef;
    }


    public NoticesScreen getNoticesScreen() {
        if (noticesScreen == null) {
            noticesScreen = new NoticesScreen();
        }
        return noticesScreen;
    }

    
}
