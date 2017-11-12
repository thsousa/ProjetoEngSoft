package Avisos;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Notification extends JPanel {
    protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 600;
	/**
	 * Create the panel.
	 */
	public Notification() {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
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
        
        setLayout(groupLayout);
        
	}
}
