package engsoft.cond.control;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import engsoft.cond.model.Usuario;
import engsoft.cond.screen.Screen;

public class MainManager {

    public static final boolean DEBUG = true;
    public static final boolean POP_DB = true;
    public static final boolean CLEAR_DB = true;

    private JFrame mainFrame;
    private static MainManager autoRef;
    private Usuario activeUser;

    private ArrayList<Screen> screenList = new ArrayList<Screen>();

    public MainManager() {

    }

    public MainManager(JFrame frame) {
        this.mainFrame = frame;
    }

    public static MainManager getInstance() {
        if (autoRef == null) {
            autoRef = new MainManager();
        }
        return autoRef;
    }

    public static MainManager getInstance(JFrame frame) {
        if (autoRef == null) {
            autoRef = new MainManager(frame);
        }
        return autoRef;
    }

    public void changeScreen(Screen newScreen) {
        screenList.add(newScreen);
        Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(newScreen);
        mainFrame.setMinimumSize(newScreen.getSize());
        mainFrame.setMaximumSize(newScreen.getSize());
        mainFrame.setPreferredSize(newScreen.getSize());
        mainFrame.pack();
        mainFrame.setLocation((monitor.width - mainFrame.getWidth()) / 2,
                (monitor.height - mainFrame.getHeight()) / 2);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void goBack() {
        Screen lastScreen = screenList.get(screenList.size() - 2);
        screenList.remove(screenList.size() - 1);
        screenList.remove(screenList.size() - 1);
        if (lastScreen != null) {
            changeScreen(lastScreen);
        }
    }

    public void logout() {
        screenList = new ArrayList<Screen>();
        activeUser = null;
        changeScreen(LoginManager.getInstance().getLoginScreen());
    }

    public void refresh() {
        mainFrame.pack();
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void setActiveUser(Usuario user) {
        this.activeUser = user;
    }

    public Usuario getActiveUser() {
        return activeUser;
    }

}
