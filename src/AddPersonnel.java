import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AddPersonnel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;
	/**
	 * Create the panel.
	 */
	public AddPersonnel(JFrame frame) {
		this.parentFrame = frame;
		setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 20, 25));
		
		JLabel personnelTypeText = new JLabel("Personnel Type:");
		personnelTypeText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(personnelTypeText);
		
		JComboBox<String> typeComboBox = new JComboBox();
		
		typeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Nurse","Doctor"}));
		centerPanel.add(typeComboBox);
		
		JLabel usernameText = new JLabel("Username:");
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(usernameText);
		
		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordText = new JLabel("Password:");
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(passwordText);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		centerPanel.add(passwordField);
		
		JLabel nameText = new JLabel("Name");
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(nameText);
		
		JTextField nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel surnameText = new JLabel("Surname:");
		surnameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(surnameText);
		
		JTextField surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(surnameField);
		surnameField.setColumns(10);
		
		JLabel genderText = new JLabel("Gender:");
		genderText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(genderText);
		
		JComboBox<String> genderField = new JComboBox();
		genderField.setModel(new DefaultComboBoxModel(new String[] {"Not Specified","Male","Female"}));
		genderField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(genderField);
		
		JLabel ageText = new JLabel("Age:");
		ageText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(ageText);
		
		JTextField ageField = new JTextField();
		ageField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(ageField);
		ageField.setColumns(10);
		
		
		
		//not visible for nurse add
		JLabel departmentText = new JLabel("Department");
		departmentText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(departmentText);
		
		JComboBox departmentComboBox = new JComboBox();
		departmentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(departmentComboBox);
		
		JLabel expertiseText = new JLabel("Expertise");
		expertiseText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(expertiseText);
		
		
			
		JComboBox<String> expertiseComboBox = new JComboBox<>();
		expertiseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		expertiseComboBox.setModel(new DefaultComboBoxModel(new String[] {null,"A","B","C","D"}));
		centerPanel.add(expertiseComboBox);
		
		departmentText.setVisible(false);
		departmentComboBox.setVisible(false);
		expertiseText.setVisible(false);
		expertiseComboBox.setVisible(false);
		
		typeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeComboBox.getSelectedItem().equals("Nurse")) {
					departmentText.setVisible(false);
					departmentComboBox.setVisible(false);
					expertiseText.setVisible(false);
					expertiseComboBox.setVisible(false);
				}
				else if (typeComboBox.getSelectedItem().equals("Doctor")){
					departmentText.setVisible(true);
					departmentComboBox.setVisible(true);
					expertiseText.setVisible(true);
					expertiseComboBox.setVisible(true);
				}
			}
		});
		//not visible for nurse add
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton addPersonnelButton = new JButton("Add Personnel");
		addPersonnelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userType = typeComboBox.getSelectedItem().toString();
				String name_surname = nameField.getText() + " " + surnameField.getText();
				int age = Integer.parseInt(ageField.getText());  // be careful
				String username = usernameField.getText();
				String gender = genderField.getSelectedItem().toString();
				int password = Integer.parseInt(new String(passwordField.getPassword()));
				if (userType.equals("Nurse")){
					if(UserController.addNurse(new Nurse(name_surname,age,username,gender,password))!= -1){
						JOptionPane.showMessageDialog(new JFrame(), "New Nurse is created");
						changePanel(parentFrame, new AddPersonnel(parentFrame));
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "Requirements is not met!!!");
					}
				}
				else {
					String department = departmentComboBox.getSelectedItem().toString();
					String expertise = expertiseComboBox.getSelectedItem().toString();
					if (UserController.addDoctor(new Doctor(name_surname,age,username,gender,1,password)) != -1){  // expertiseid
						JOptionPane.showMessageDialog(new JFrame(), "New Doctor is created");
						changePanel(parentFrame, new AddPersonnel(parentFrame));
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Requirements is not met!!!");
					}

				}

			}
		});
		addPersonnelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		addPersonnelButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel.add(addPersonnelButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame));
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancelButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel.add(cancelButton);
		
	}
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

}
