package engsoft.cond.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import engsoft.cond.control.MainManager;
import engsoft.cond.control.SignupManager;
import engsoft.cond.model.Usuario;
import static engsoft.cond.util.Constants.*;

public class MainMenuScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;


    public MainMenuScreen() {
    }

    public MainMenuScreen(Usuario user) {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Menu princpal", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Bem vindo, " + user.getNome() + "!");
        welcome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel noCondo = new JLabel();

        boolean hasNoCondo = user.getCondominios().isEmpty();
        if (hasNoCondo) {
            noCondo = new JLabel("Você não está cadastrado em nenhum condomínio, entre em contato com seu síndico ou administradora.");
            noCondo.setAlignmentX(CENTER_ALIGNMENT);
        }

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(welcome);
        if (hasNoCondo) {
            this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
            this.add(noCondo);
        } else {
            if (user.getNivel_acesso().contains(SYSADMIN_LEVEL)) {
                // TODO montar menu de sys admin
            }

            if (user.getNivel_acesso().contains(ADMIN_LEVEL)) {
                // TODO montar menu de admin
            }

            if (user.getNivel_acesso().contains(SIND_LEVEL)) {
                // TODO montar menu de s�ndico
            }

            if (user.getNivel_acesso().contains(FUNC_LEVEL)) {
                // TODO montar menu de funcion�rio
            }

            if (user.getNivel_acesso().contains(MOR_LEVEL)) {
                buildMorMenu();
            }
            this.addLogoutButton();
        }

    }

    private void buildMorMenu() {
        JButton avisos = new JButton("Mural de avisos");
        avisos.setAlignmentX(CENTER_ALIGNMENT);
        avisos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO mudar para tela de avisos
                // MainManager.getInstance().changeScreen(NoticesManager.getInstance().getNoticesScreen())
            }
        });
        JButton reserv = new JButton("Reservas de áreas comuns");
        reserv.setAlignmentX(CENTER_ALIGNMENT);
        reserv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO mudar para tela de reservas
                // MainManager.getInstance().changeScreen(ReservationsManager.getInstance().getReservationsScreen())
            }
        });
        JButton updateInfo = new JButton("Atualizar cadastro");
        updateInfo.setAlignmentX(CENTER_ALIGNMENT);
        updateInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(MainManager.getInstance().getActiveUser()));
            }
        });

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(avisos);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(reserv);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(updateInfo);
    }


}
