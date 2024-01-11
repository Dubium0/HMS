import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientMainPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userId;
	private JFrame parentFrame;
	/**
	 * Create the panel.
	 */
	public PatientMainPage(JFrame frame, int userId) {
		this.parentFrame = frame;
		this.userId = userId;
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{29, 268, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 6.0, 6.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 12.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton makeAppointmetButton = new JButton("Make Appointment");
		makeAppointmetButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		makeAppointmetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new MakeAppointmentPage(parentFrame,userId));


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
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}


}
