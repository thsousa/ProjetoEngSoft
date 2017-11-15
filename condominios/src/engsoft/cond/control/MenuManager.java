package engsoft.cond.control;

import engsoft.cond.screen.MainMenuScreen;

public class MenuManager {

    
    private static MenuManager autoRef;
    
    private MainMenuScreen moradorMenu;
    
    
    public MenuManager() {
        
    }
    
    public static MenuManager getInstance() {
        if (autoRef == null) {
            autoRef = new MenuManager();
        }
        return autoRef;
    }
    
    public MainMenuScreen getMainMenu() {
        moradorMenu = new MainMenuScreen(MainManager.getInstance().getActiveUser(), false);
        return moradorMenu;
    }

    public MainMenuScreen getMainMenu(boolean admSwitch) {
        moradorMenu = new MainMenuScreen(MainManager.getInstance().getActiveUser(), admSwitch);
        return moradorMenu;
    }
}
