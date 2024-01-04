import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminMainPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;

	/**
	 * Create the panel.
	 */
	public AdminMainPage(JFrame frame) {
		this.parentFrame = frame;
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{29, 150, 150, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 6.0, 6.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 12.0, 12.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton addPersonnelButton = new JButton("Add Personnel");
		addPersonnelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AddPersonnel(parentFrame));
			}
		});
		addPersonnelButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		GridBagConstraints gbc_addPersonnelButton = new GridBagConstraints();
		gbc_addPersonnelButton.fill = GridBagConstraints.BOTH;
		gbc_addPersonnelButton.insets = new Insets(0, 0, 5, 5);
		gbc_addPersonnelButton.gridx = 1;
		gbc_addPersonnelButton.gridy = 1;
		add(addPersonnelButton, gbc_addPersonnelButton);
		
		JButton showPersonnelButton = new JButton("Show Personnel");
		showPersonnelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new ShowPersonnel(parentFrame));
			}
		});
		showPersonnelButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_showPersonnelButton = new GridBagConstraints();
		gbc_showPersonnelButton.insets = new Insets(0, 0, 5, 5);
		gbc_showPersonnelButton.fill = GridBagConstraints.BOTH;
		gbc_showPersonnelButton.gridx = 2;
		gbc_showPersonnelButton.gridy = 1;
		add(showPersonnelButton, gbc_showPersonnelButton);
		
		
		
		JButton addRoomButton = new JButton("Rooms");
		addRoomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new ShowRoom(parentFrame));
			}
		});
		addRoomButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addRoomButton = new GridBagConstraints();
		gbc_addRoomButton.insets = new Insets(0, 0, 5, 5);
		gbc_addRoomButton.fill = GridBagConstraints.BOTH;
		gbc_addRoomButton.gridx = 1;
		gbc_addRoomButton.gridy = 2;
		add(addRoomButton, gbc_addRoomButton);
		
		
		
		JButton addExpertiseButton = new JButton("Specialties");
		addExpertiseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new ShowSpecialty(parentFrame));
			}
		});
		addExpertiseButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addExpertiseButton = new GridBagConstraints();
		gbc_addExpertiseButton.insets = new Insets(0, 0, 5, 5);
		gbc_addExpertiseButton.fill = GridBagConstraints.BOTH;
		gbc_addExpertiseButton.gridx = 2;
		gbc_addExpertiseButton.gridy = 2;
		add(addExpertiseButton, gbc_addExpertiseButton);


	}
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}
	

}
