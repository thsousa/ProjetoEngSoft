package engsoft.cond.screen;

import static engsoft.cond.util.Constants.ADMIN_LEVEL;
import static engsoft.cond.util.Constants.FUNC_LEVEL;
import static engsoft.cond.util.Constants.MOR_LEVEL;
import static engsoft.cond.util.Constants.SIND_LEVEL;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import engsoft.cond.control.MainManager;
import engsoft.cond.model.Usuario;

public class ReservationsScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;

    public ReservationsScreen() {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(getSize());
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Reservas de áreas internas", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        
        Usuario user = MainManager.getInstance().getActiveUser();
        
        if (user.getNivel_acesso().contains(ADMIN_LEVEL) || user.getNivel_acesso().contains(SIND_LEVEL)) {
            // TODO: Fazer a tela versão síndico
        }
        
        if (user.getNivel_acesso().contains(MOR_LEVEL) || user.getNivel_acesso().contains(FUNC_LEVEL)) {
            // TODO: Fazer a tela versão morador
        }
        
        this.addBackButton();
        
    }

}
