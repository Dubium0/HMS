import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorMainPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userId;
	private JFrame parentFrame;
	/**
	 * Create the panel.
	 */
	public DoctorMainPage(JFrame frame,int userId) {
		this.parentFrame = frame;
		this.userId = userId;
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{29, 268, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 6.0, 6.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 12.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton calendarButton = new JButton("My Calendar");
		calendarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new DoctorAvailabilityPage(parentFrame,userId));
			}
		});
		calendarButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_calendarButton = new GridBagConstraints();
		gbc_calendarButton.fill = GridBagConstraints.BOTH;
		gbc_calendarButton.insets = new Insets(0, 0, 5, 5);
		gbc_calendarButton.gridx = 1;
		gbc_calendarButton.gridy = 1;
		add(calendarButton, gbc_calendarButton);
		
		JButton btnNewButton_1 = new JButton("Appointments");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new DoctorAppointmentPage(parentFrame,userId));
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		add(btnNewButton_1, gbc_btnNewButton_1);



	}
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

}
