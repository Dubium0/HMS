import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddPersonnel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;
	private int userId;
	/**
	 * Create the panel.
	 */
	public AddPersonnel(JFrame frame,int userId) {
		this.parentFrame = frame;
		this.userId = userId;
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


		class IntegerDocumentFilter extends DocumentFilter {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
				if (string.matches("\\d+")) {
					super.insertString(fb, offset, string, attr);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				if (text.matches("\\d+")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		}
		JTextField ageField = new JTextField();
		ageField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		((AbstractDocument) ageField.getDocument()).setDocumentFilter(new IntegerDocumentFilter());
		centerPanel.add(ageField);
		ageField.setColumns(10);
		
		
		
		//not visible for nurse add





		ArrayList<Expertise> expertiseArrayList = EntityController.getExpertises();
		ArrayList<String> expertiseNames = new ArrayList<>();
		for (Expertise expertise: expertiseArrayList){
			expertiseNames.add(expertise.name);

		}


		JLabel expertiseText = new JLabel("Expertise");
		expertiseText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(expertiseText);


			
		JComboBox<String> expertiseComboBox = new JComboBox<>();
		expertiseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		expertiseComboBox.setModel(new DefaultComboBoxModel(expertiseNames.toArray()));
		centerPanel.add(expertiseComboBox);

		expertiseText.setVisible(false);
		expertiseComboBox.setVisible(false);
		
		typeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeComboBox.getSelectedItem().equals("Nurse")) {
					expertiseText.setVisible(false);
					expertiseComboBox.setVisible(false);
				}
				else if (typeComboBox.getSelectedItem().equals("Doctor")){
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
				String personnelType = typeComboBox.getSelectedItem().toString();
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String name = nameField.getText();
				String surname = surnameField.getText();
				String gender = genderField.getSelectedItem().toString();
				int age = 0;
				try{
					age = Integer.parseInt(ageField.getText());
				}catch (Exception e1){
				}
				if (!AccountValidator.validateUsername(username)){
					JOptionPane.showMessageDialog(new JFrame(), "Username already exists!");
				}
				else if (!AccountValidator.validatePassword(password)){
					JOptionPane.showMessageDialog(new JFrame(), "Password must be at least 6 characters!");
				}
				else if(!AccountValidator.validateAge(age)){
					JOptionPane.showMessageDialog(new JFrame(), "Invalid age!");
				}else {
					if (personnelType.equals("Nurse")){
						if (UserController.addNurse(new Nurse((name + " " + surname),age,username,gender,password))!= -1){
							JOptionPane.showMessageDialog(new JFrame(), "Nurse account is added");
							changePanel(parentFrame,new AddPersonnel(parentFrame,userId));

						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Requirements is not met");
						}
					}
					else {
						String expertiseName = expertiseComboBox.getSelectedItem().toString();
						int expertiseId = EntityController.getExpertiseByName(expertiseName).expertise_id;
						if (UserController.addDoctor(new Doctor((name + " " + surname),age,username,gender,expertiseId,password))!= -1){
							JOptionPane.showMessageDialog(new JFrame(), "Doctor account is added");
							changePanel(parentFrame,new AddPersonnel(parentFrame,userId));

						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Requirements is not met");
						}
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
				changePanel(parentFrame,new AdminMainPage(parentFrame,userId));
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
