package engsoft.cond.screen;

import static engsoft.cond.util.Constants.ADMIN_LEVEL;
import static engsoft.cond.util.Constants.MOR_LEVEL;
import static engsoft.cond.util.Constants.SIND_LEVEL;
import static engsoft.cond.util.Constants.FUNC_LEVEL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;

import engsoft.cond.control.MainManager;
import engsoft.cond.model.Mural;
import engsoft.cond.model.Usuario;

public class NoticesScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;

    public NoticesScreen(Mural mural) {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(getSize());
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Mural de Avisos", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);        

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(buildDefaultContents(mural));
        
        Usuario user = MainManager.getInstance().getActiveUser();
        
        if (user.getNivel_acesso().contains(ADMIN_LEVEL) || user.getNivel_acesso().contains(SIND_LEVEL)) {
            // TODO: Fazer a tela versão síndico
        }

        if (user.getNivel_acesso().contains(FUNC_LEVEL)) {
            // TODO: Fazer a tela versão funcionário
        }

        if (user.getNivel_acesso().contains(MOR_LEVEL)) {
            // TODO: Fazer a tela versão morador
        }

        this.addBackButton();
        
    }
    
    
    private JPanel buildDefaultContents(Mural mural) {
        final JPanel mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainList.add(new Review(), gbc);
        
        
        JScrollPane panel= new JScrollPane(mainList);
        
        JButton addComent = new JButton("Adicionar Comentario");
        addComent.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                JPanel coment = new Review();
                coment.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;
                mainList.add(coment, gbc, 0);

                validate();
                repaint();
                }
        });
        addComent.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 12));
        
        JTextArea warn = new JTextArea();
        warn.setText("AVISOS");
        
        JLabel lblTopico = new JLabel("TOPICO");
        lblTopico.setHorizontalAlignment(SwingConstants.CENTER);
        lblTopico.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(31)
                                                .addComponent(lblTopico, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                .addGap(286)
                                                .addComponent(addComent, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(105)
                                                .addComponent(warn, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(72)
                                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(130, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(24)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(addComent)
                                        .addComponent(lblTopico, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(warn, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                .addGap(64))
        );
        
        return(mainList);
        
    }
    
    private class Review extends JPanel {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /**
         * Create the panel.
         */
        public Review() {
                this.setSize(446, 71);
                
                JLabel lblIcon = new JLabel("Icon");
                
                JTextArea textArea = new JTextArea();
                GroupLayout groupLayout = new GroupLayout(this);
                groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addGap(26)
                                        .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblIcon)
                                        .addGap(23))
                                .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                );
                setLayout(groupLayout);


        }
    }


}


