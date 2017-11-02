package engsoft.cond.screen;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import engsoft.cond.control.MainManager;

public class Screen extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5551074437665635282L;
    
    private JLabel err;
    
    public Screen() {
        
    }
    
    public void addError(String title, String content) {
        if (err != null) {
            removeError();
        }
        err = new JLabel("ERRO: " + title + " - " + content);
        err.setFont(new Font("Arial", Font.BOLD, 12));
        err.setForeground(Color.RED);
        this.add(err);
        MainManager.getInstance().refresh();
    }
    
    public void removeError() {
        this.remove(err);
        err = null;
        MainManager.getInstance().refresh();        
    }

}
