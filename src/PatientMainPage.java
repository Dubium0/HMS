import javax.swing.JPanel;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JFrame;
public class PatientMainPage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PatientMainPage(JFrame frame) {
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{29, 268, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 6.0, 6.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 12.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton makeAppointmetButton = new JButton("Make Appointment");
		makeAppointmetButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		makeAppointmetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_makeAppointmetButton = new GridBagConstraints();
		gbc_makeAppointmetButton.fill = GridBagConstraints.BOTH;
		gbc_makeAppointmetButton.insets = new Insets(0, 0, 5, 5);
		gbc_makeAppointmetButton.gridx = 1;
		gbc_makeAppointmetButton.gridy = 1;
		add(makeAppointmetButton, gbc_makeAppointmetButton);
		
		JButton showAppointmetButton = new JButton("Show Appointment");
		showAppointmetButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_showAppointmetButton = new GridBagConstraints();
		gbc_showAppointmetButton.insets = new Insets(0, 0, 5, 5);
		gbc_showAppointmetButton.fill = GridBagConstraints.BOTH;
		gbc_showAppointmetButton.gridx = 2;
		gbc_showAppointmetButton.gridy = 1;
		add(showAppointmetButton, gbc_showAppointmetButton);
	}

}
