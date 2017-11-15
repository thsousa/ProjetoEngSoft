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

import engsoft.cond.control.CondoManager;
import engsoft.cond.control.DatabaseManager;
import engsoft.cond.control.MainManager;
import engsoft.cond.model.AreaComum;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;

public class CondoScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = -5258163305153901400L;
    
    private JPanel contents;
    private JScrollPane buildings;
    private JScrollPane sinds;
    private JScrollPane areas;
    private JList<Condominio> bldg_list;
    private JList<Usuario> sind_list;
    private JList<AreaComum> areas_list;
    private DefaultListModel<Usuario> sind_lmodel;
    private DefaultListModel<Condominio> bldg_lmodel;
    private DefaultListModel<AreaComum> areas_lmodel;

    public CondoScreen() {

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
        
        sind_lmodel = new DefaultListModel<>();
        bldg_lmodel = new DefaultListModel<>();
        areas_lmodel = new DefaultListModel<>();
        
        bldg_list = new JList<>(bldg_lmodel);
        bldg_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bldg_list.setLayoutOrientation(JList.VERTICAL_WRAP);
        
        bldg_list.addListSelectionListener(new ListSelectionListener() {
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                CondoManager.getInstance().handleBldgSelection(bldg_list.getSelectedValue());                
            }
        });
        
        sind_list = new JList<>(sind_lmodel);
        sind_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sind_list.setLayoutOrientation(JList.VERTICAL_WRAP);
        
        areas_list = new JList<>(areas_lmodel);
        areas_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        areas_list.setLayoutOrientation(JList.VERTICAL_WRAP);
        
        buildings = new JScrollPane(bldg_list);
        sinds = new JScrollPane(sind_list);
        areas = new JScrollPane(areas_list);
        
        buildings.setSize(contents.getSize().width/3 - 10, contents.getSize().height - 10);
        sinds.setSize(contents.getSize().width/3 - 10, contents.getSize().height - 10);
        areas.setSize(contents.getSize().width/3 - 10, contents.getSize().height - 10);
        
        contents.add(buildings);
        contents.add(areas);
        contents.add(sinds);
        
        JButton acessar_como_sindico = new JButton("Acessar condomínio como síndico");
        
        acessar_como_sindico.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {                
                CondoManager.getInstance().handleSindAccess(bldg_list.getSelectedValue());                
            }
        });
        
        JButton adicionar_area = new JButton("Adicionar Área");
        
        adicionar_area.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO abrir diálogo de nova area                 
            }
        });
        
        JButton remover_area = new JButton("Remover Área.");
        
        remover_area.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                AreaComum removido = areas_list.getSelectedValue();
                if (removido != null) {
                    // TODO remover corretamente 
                    DatabaseManager.getInstance().removeEntity(removido);
                    CondoManager.getInstance().repopScreenLists();
                }                       
            }
        });
        
        JButton adicionar_sind = new JButton("Adicionar sind.");
        
        adicionar_sind.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO abrir diálogo de novo sind
                
            }
        });
        
        JButton remover_sind = new JButton("Remover sind.");
        
        remover_sind.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario sind = sind_list.getSelectedValue();
                if (sind != null) { 
                    DatabaseManager.getInstance().removeUserFromBuilding(sind, bldg_list.getSelectedValue());
                    CondoManager.getInstance().repopScreenLists();
                }
            }
        });
        
        JPanel buttons = new JPanel();
        buttons.setSize(DEFAULT_WIDTH - 50, 150);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(acessar_como_sindico);
        buttons.add(adicionar_area);
        buttons.add(remover_area);
        buttons.add(adicionar_sind);
        buttons.add(remover_sind);        

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(contents);        
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(buttons);        
        this.addBackButton();
        
    }
    
    public void populateBuildingsPanel(ArrayList<Condominio> arrayList) {
        
        bldg_lmodel.clear();
        
        for (Condominio bldg : arrayList) {
            bldg_lmodel.addElement(bldg);
        }
        
        MainManager.getInstance().refresh();
        
    }
    
    public void populateSindsPanel(ArrayList<Usuario> sinds) {
        
        sind_lmodel.clear();
        
        for (Usuario sind : sinds) {
            sind_lmodel.addElement(sind);
        }
        
        MainManager.getInstance().refresh();
        
    }
    
    public void populateAreasPanel(ArrayList<AreaComum> areas) {
        
        areas_lmodel.clear();
        
        for (AreaComum area : areas) {
            areas_lmodel.addElement(area);
        }
        
        MainManager.getInstance().refresh();
        
    }


}
