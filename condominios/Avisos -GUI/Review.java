import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Review extends JPanel {

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
