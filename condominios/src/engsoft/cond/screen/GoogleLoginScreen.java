package engsoft.cond.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import engsoft.cond.control.LoginManager;

public class GoogleLoginScreen extends Screen {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    public GoogleLoginScreen() {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);


        JLabel desc = new JLabel("Faça login pela página do navegador que abriu.", JLabel.CENTER);
        desc.setAlignmentX(CENTER_ALIGNMENT);

        JLabel info = new JLabel("Copie o código de autenticação: ", JLabel.CENTER);
        info.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField cod = new JTextField();
        cod.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        cod.setAlignmentX(CENTER_ALIGNMENT);
        cod.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
	                LoginManager.getInstance().doGoogleLogin(cod.getText());
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});


        JButton googleLogin = new JButton("Finalizar login");
        googleLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginManager.getInstance().doGoogleLogin(cod.getText());
            }
        });
        googleLogin.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(desc);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(info);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(cod);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(googleLogin);
        this.addBackButton();

    }

}
