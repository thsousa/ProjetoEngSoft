package engsoft.cond.control;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainManager {
    
    private JFrame mainFrame;
    private static MainManager autoRef;
    
    
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
    
    public void changeScreen(JPanel newScreen) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(newScreen);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    

}
