package Reserva;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

public class ReservaGUI extends JPanel {

	private JTextField textField_2;
	private String comboBoxItems[] = { "Data :", "Nome :" };
	private JComboBox<Object> comboBox2;
	private JComboBox<Object> comboBox_1 = new JComboBox<Object>(comboBoxItems);
	private JComboBox<Object> comboBox_2 = new JComboBox<Object>(comboBoxItems);
	private JDateChooser dateChooser;
	private JTable table;
	private JTable table_1;
	JDateChooser dateChooser_1;
	
	protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 600;
	

	/**
	 * Initialize the contents of the frame.
	 */
	public ReservaGUI() {
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("AGENDAR", null, panel, null);
		
		JLabel lblNewLabel_2 = new JLabel("Data :");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));
		
		JButton btnNewButton = new JButton("Agendar");
		btnNewButton.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		
		dateChooser = new JDateChooser("dd/MM/yyyy","##/##/####",'_');
		
		JLabel lblNewLabel_4 = new JLabel("Tipo :");
		lblNewLabel_4.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));
		
		String [] op={"Sal�o de Festas","Churrasqueira"};
		comboBox2 = new JComboBox<Object>(op);
		comboBox2.setSelectedIndex(1);
		comboBox2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
						.addComponent(comboBox2, Alignment.LEADING, 0, 547, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(178)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addGap(219))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(134)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(130)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		
		
		final JPanel cards = new JPanel(new CardLayout());
		tabbedPane.addTab("CONSULTAR", null, cards, null);
		
		
		JPanel card2 = new JPanel();
		
		JLabel lblConsulta = new JLabel("CONSULTA:");
		lblConsulta.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		comboBox_1.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		

		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
			    CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, (String)evt.getItem());
			    comboBox_2.setSelectedIndex(0);
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 15));
		textField_2.setColumns(10);
		
		JButton button = new JButton("Pesquisar");
		button.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		
		JScrollPane scrollPane = new JScrollPane(table = new JTable());
		table.setFillsViewportHeight(true);
		
		GroupLayout gl_card2 = new GroupLayout(card2);
		gl_card2.setHorizontalGroup(
			gl_card2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_card2.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_card2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
						.addGroup(gl_card2.createSequentialGroup()
							.addGroup(gl_card2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConsulta)
								.addGroup(gl_card2.createSequentialGroup()
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addGap(107))
		);
		gl_card2.setVerticalGroup(
			gl_card2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_card2.createSequentialGroup()
					.addGap(28)
					.addComponent(lblConsulta)
					.addGap(18)
					.addGroup(gl_card2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_card2.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(49))
		);
		card2.setLayout(gl_card2);
		
		
		JPanel card1 = new JPanel();

		JLabel lblConsulta_2 = new JLabel("CONSULTA:");
		lblConsulta_2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		comboBox_2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt1) {
			    CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, (String)evt1.getItem());
			    comboBox_1.setSelectedIndex(1);
			}
		});
				
		
		JButton btnNewButton_2 = new JButton("Pesquisar");
		btnNewButton_2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		
		dateChooser_1 = new JDateChooser();
		JScrollPane scrollPane_1 = new JScrollPane(table_1 = new JTable());
		table_1.setFillsViewportHeight(true);


		GroupLayout gl_card1 = new GroupLayout(card1);
		gl_card1.setHorizontalGroup(
			gl_card1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_card1.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_card1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_card1.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_card1.createSequentialGroup()
							.addGroup(gl_card1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConsulta_2)
								.addGroup(gl_card1.createSequentialGroup()
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dateChooser_1, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(106))))
		);
		gl_card1.setVerticalGroup(
			gl_card1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_card1.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_card1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_card1.createSequentialGroup()
							.addGap(28)
							.addComponent(lblConsulta_2)
							.addGap(18)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_card1.createSequentialGroup()
							.addGap(66)
							.addGroup(gl_card1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		card1.setLayout(gl_card1);
		
		cards.add(card1,"Data :");
		cards.add(card2,"Nome :");
		//cards.add(card2,"Data :");
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("REMOVER", null, panel_2, null);
		
		JLabel label_1 = new JLabel("REMOVE:");
		label_1.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		JLabel label_2 = new JLabel("Data :");
		label_2.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		JLabel label_3 = new JLabel("Tipo :");
		label_3.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		JDateChooser dateChooser_2 = new JDateChooser();
		
		JComboBox<Object> comboBox_3 = new JComboBox<Object>(op);
		comboBox_3.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 15));
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_2);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(44)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(118)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser_2, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
								.addComponent(comboBox_3, 0, 245, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(28)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnApagar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(dateChooser_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(203, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_3);
		
	}
}
