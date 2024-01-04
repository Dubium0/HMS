import javax.swing.JPanel;


import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
public class NurseMainPage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public NurseMainPage(JFrame frame) {
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{29, 268, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 6.0, 6.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 12.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton calendarButton = new JButton("My Calendar");
		calendarButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		calendarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_calendarButton = new GridBagConstraints();
		gbc_calendarButton.fill = GridBagConstraints.BOTH;
		gbc_calendarButton.insets = new Insets(0, 0, 5, 5);
		gbc_calendarButton.gridx = 1;
		gbc_calendarButton.gridy = 1;
		add(calendarButton, gbc_calendarButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		add(btnNewButton_1, gbc_btnNewButton_1);

	}

}
