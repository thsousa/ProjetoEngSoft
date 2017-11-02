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
        if (moradorMenu == null) {
            moradorMenu = new MainMenuScreen(MainManager.getInstance().getActiveUser());
        } 
        return moradorMenu;
    }
}
