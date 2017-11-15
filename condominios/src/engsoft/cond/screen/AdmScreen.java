package engsoft.cond.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import engsoft.cond.control.AdmManager;
import engsoft.cond.control.DatabaseManager;
import engsoft.cond.control.MainManager;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;

public class AdmScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;
    
    private JPanel contents;
    private JScrollPane buildings;
    private JScrollPane adms;
    private JList<Condominio> bldg_list;
    private JList<Usuario> adm_list;
    private DefaultListModel<Usuario> adm_lmodel;
    private DefaultListModel<Condominio> bldg_lmodel;

    public AdmScreen() {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(getSize());
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Gerenciamento de condomínios", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);
        
        contents = new JPanel(); 
        contents.setSize(DEFAULT_WIDTH - 50, DEFAULT_HEIGHT - 250);
        contents.setLayout(new BoxLayout(contents, BoxLayout.X_AXIS));
        
        adm_lmodel = new DefaultListModel<>();
        bldg_lmodel = new DefaultListModel<>();
        
        bldg_list = new JList<>(bldg_lmodel);
        bldg_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bldg_list.setLayoutOrientation(JList.VERTICAL_WRAP);
        
        bldg_list.addListSelectionListener(new ListSelectionListener() {
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                AdmManager.getInstance().handleBldgSelection(bldg_list.getSelectedValue());                
            }
        });
        
        adm_list = new JList<>(adm_lmodel);
        adm_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adm_list.setLayoutOrientation(JList.VERTICAL_WRAP);
        
        buildings = new JScrollPane(bldg_list);
        adms = new JScrollPane(adm_list);
        
        buildings.setSize(contents.getSize().width/2 - 10, contents.getSize().height - 10);
        adms.setSize(contents.getSize().width/2 - 10, contents.getSize().height - 10);
        
        contents.add(buildings);
        contents.add(adms);
        
        JButton adicionar_cond = new JButton("Adicionar cond.");
        
        adicionar_cond.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO abrir diálogo de novo condomínio                
            }
        });
        
        JButton remover_cond = new JButton("Remover cond.");
        
        remover_cond.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Condominio removido = bldg_list.getSelectedValue();
                if (removido != null) {
                    DatabaseManager.getInstance().removeBuilding(removido);
                    AdmManager.getInstance().repopScreenLists();
                }                       
            }
        });
        
        JButton adicionar_adm = new JButton("Adicionar adm.");
        
        adicionar_adm.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO abrir diálogo de nova adm
                
            }
        });
        
        JButton remover_adm = new JButton("Remover adm.");
        
        remover_adm.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario adm = adm_list.getSelectedValue();
                if (adm != null) { 
                    DatabaseManager.getInstance().removeUserFromBuilding(adm, bldg_list.getSelectedValue());
                    AdmManager.getInstance().repopScreenLists();
                }
            }
        });
        
        JPanel buttons = new JPanel();
        buttons.setSize(DEFAULT_WIDTH - 50, 150);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(adicionar_cond);
        buttons.add(remover_cond);
        buttons.add(adicionar_adm);
        buttons.add(remover_adm);        

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(contents);        
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(buttons);        
        this.addBackButton();
        
    }
    
    public void populateBuildingsPanel(ArrayList<Object> bldgs) {
        
        bldg_lmodel.clear();
        
        for (Object b : bldgs) {
            Condominio bldg = (Condominio) b;
            bldg_lmodel.addElement(bldg);
        }
        
        MainManager.getInstance().refresh();
        
    }
    
    public void populateAdmsPanel(ArrayList<Usuario> adms) {
        
        adm_lmodel.clear();
        
        for (Usuario adm : adms) {
            adm_lmodel.addElement(adm);
        }
        
        MainManager.getInstance().refresh();
        
    }

}
