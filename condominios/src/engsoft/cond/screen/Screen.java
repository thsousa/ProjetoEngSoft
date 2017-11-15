package engsoft.cond.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engsoft.cond.control.MainManager;

public class Screen extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -5551074437665635282L;
    protected static final int DEFAULT_WIDTH = 1024;
    protected static final int DEFAULT_HEIGHT = 720;

    private JLabel err;

    public Screen() {

    }

    protected void addBackButton() {
    	JButton back = new JButton("Voltar");
    	back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainManager.getInstance().goBack();
			}
		});
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 30)));
    	this.add(back);
        MainManager.getInstance().refresh();
    }

    protected void addLogoutButton() {
    	JButton logout = new JButton("Logout");
    	logout.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			MainManager.getInstance().logout();
    		}
    	});
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 30)));
    	this.add(logout);
        MainManager.getInstance().refresh();
    }

    public void addError(String title, String content) {
        if (err != null) {
            removeError();
        }
        err = new JLabel("ERRO: " + title + " - " + content);
        err.setFont(new Font("Arial", Font.BOLD, 12));
        err.setForeground(Color.RED);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 30)));
        this.add(err);
        MainManager.getInstance().refresh();
    }

    public void removeError() {
        this.remove(err);
        err = null;
        MainManager.getInstance().refresh();
    }

}
