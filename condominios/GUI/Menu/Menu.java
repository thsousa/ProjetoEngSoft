package Menu;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class Menu extends JPanel {
	
	protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 600;

	/**
	 * Create the panel.
	 */
	public Menu() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		GridBagLayout gridBagLayout = new GridBagLayout();

		setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Mural de Avisos");
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.ipady = 30;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 30);
		gbc_btnNewButton.ipadx = 20;
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reservas de areas comuns");
		btnNewButton_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 30, 0, 0);
		gbc_btnNewButton_1.ipady = 20;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.ipadx = 20;
		gbc_btnNewButton_1.gridx = 13;
		gbc_btnNewButton_1.gridy = 9;
		add(btnNewButton_1, gbc_btnNewButton_1);

	}

}
