package engsoft.cond.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import engsoft.cond.control.AdmManager;
import engsoft.cond.control.CondoManager;
import engsoft.cond.control.MainManager;
import engsoft.cond.control.MorManager;
import engsoft.cond.control.NoticesManager;
import engsoft.cond.control.ReservationsManager;
import engsoft.cond.control.SignupManager;
import engsoft.cond.model.Usuario;
import static engsoft.cond.util.Constants.*;

public class MainMenuScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;

    public MainMenuScreen() {
    }

    public MainMenuScreen(Usuario user, boolean admSwitch) {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(getSize());
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Menu princpal", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Bem vindo, " + user.getNome() + "!");
        welcome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel noCondo = new JLabel();

        boolean hasNoCondo = user.getCondominios().isEmpty() && (!user.getNivel_acesso().contains(SYSADMIN_LEVEL));
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
                buildSysAdmMenu();
            }

            if (user.getNivel_acesso().contains(ADMIN_LEVEL)) {
                buildAdmMenu();
            }

            if (user.getNivel_acesso().contains(SIND_LEVEL)) {
                buildSindMenu();
            }             

            if (user.getNivel_acesso().contains(MOR_LEVEL) || user.getNivel_acesso().contains(SIND_LEVEL) || user.getNivel_acesso().contains(FUNC_LEVEL)) {
                buildMorMenu();
            }
            
            this.addLogoutButton();
            
            if (admSwitch) {
                JButton voltarMenuAdm = new JButton("Voltar ao gerenciamento de condomínios");
                voltarMenuAdm.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainManager.getInstance().swapTempUser(null);
                        MainManager.getInstance().changeScreen(CondoManager.getInstance().getCondoScreen(false));
                        MainManager.getInstance().cleanseScreenList();
                    }
                });
                this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
                this.add(voltarMenuAdm);
            }
            
        }

    }

    private void buildMorMenu() {
        JButton avisos = new JButton("Mural de avisos");
        avisos.setAlignmentX(CENTER_ALIGNMENT);
        avisos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(NoticesManager.getInstance().getNoticesScreen(MainManager.getInstance().getActiveUser().getCondominios().get(0).getMural()));
            }
        });
        JButton reserv = new JButton("Reservas de Áreas comuns");
        reserv.setAlignmentX(CENTER_ALIGNMENT);
        reserv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(ReservationsManager.getInstance().getReservationsScreen(MainManager.getInstance().getActiveUser().getCondominios().get(0)));
            }
        });
        JButton updateInfo = new JButton("Atualizar cadastro");
        updateInfo.setAlignmentX(CENTER_ALIGNMENT);
        updateInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(SignupManager.getInstance().getSignupScreen(MainManager.getInstance().getActiveUser(), true));
            }
        });

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(avisos);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(reserv);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(updateInfo);
    }
    
    private void buildSysAdmMenu() {
        
        JButton cadAdm = new JButton("Gerenciar condomínios/administradoras");
        cadAdm.setAlignmentX(CENTER_ALIGNMENT);
        cadAdm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(AdmManager.getInstance().getAdmScreen());
            }
        });
        
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(cadAdm);
        
    }
    
    private void buildAdmMenu() {
        
        JButton gerCond = new JButton("Gerenciar condomínios");
        gerCond.setAlignmentX(CENTER_ALIGNMENT);
        gerCond.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(CondoManager.getInstance().getCondoScreen());
            }
        });
        
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(gerCond);
        
    }
    
    private void buildSindMenu() {
        
        JButton gerMor = new JButton("Gerenciar condôminos");
        gerMor.setAlignmentX(CENTER_ALIGNMENT);
        gerMor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainManager.getInstance().changeScreen(MorManager.getInstance().getMorScreen());
            }
        });         
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(gerMor);
        
    }


}
