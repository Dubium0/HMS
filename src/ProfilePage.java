import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ProfilePage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame;
	private int userId;
	/**
	 * Create the panel.
	 */
	public ProfilePage( JFrame frame, int userId) {
		this.parentFrame = frame;
		this.userId = userId;
        int userType = LoginController.getAccountType(userId);
        User user = UserController.getUser(userId);
        String userTypeName = "";
        if (userType == LoginController.PATIENT){
            userTypeName = "Patient";
        }
        else if (userType == LoginController.DOCTOR){
            userTypeName = "Doctor";
        }
        else if (userType == LoginController.NURSE){
            userTypeName = "Nurse";
        }
        else if (userType == LoginController.ADMIN){
            userTypeName = "Admin";
        }


        this.setLayout(new GridLayout(0, 1, 0, 20));

        JLabel userIdText = new JLabel("userId: " + user.user_id  );
        userIdText.setFont(new Font("Tahoma", Font.BOLD, 31));
        userIdText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(userIdText);

        JLabel usernameText = new JLabel("Username: " + user.user_name);
        usernameText.setFont(new Font("Tahoma", Font.BOLD, 31));
        usernameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(usernameText);
        
        JLabel nameText = new JLabel("Name: " + user.name_surname );
        nameText.setFont(new Font("Tahoma", Font.BOLD, 31));
        nameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(nameText);

        
        JLabel ageText = new JLabel("Age: " + user.age );
        ageText.setHorizontalAlignment(SwingConstants.CENTER);
        ageText.setFont(new Font("Tahoma", Font.BOLD, 31));
        this.add(ageText);
        
        JLabel genderText = new JLabel("Gender: " + user.gender );
        genderText.setFont(new Font("Tahoma", Font.BOLD, 31));
        genderText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(genderText);



        JLabel roleText = new JLabel("Role: " + userTypeName );
        roleText.setFont(new Font("Tahoma", Font.BOLD, 31));
        roleText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(roleText);





        if (userType == LoginController.DOCTOR) {  // if it is doctor
            Doctor doctor = (Doctor) user;
            String expertise_name = EntityController.getExpertiseByID(doctor.expertise_id).name;
            String department = EntityController.getDepartmentByID(EntityController.getExpertiseByID(doctor.expertise_id).department_id).name;
        	JLabel departmentText = new JLabel("Department: " + department );
        	departmentText.setFont(new Font("Tahoma", Font.BOLD, 31));
        	departmentText.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(departmentText);
            
            JLabel expertiseText = new JLabel("Expertise: " + expertise_name );
            expertiseText.setFont(new Font("Tahoma", Font.BOLD, 31));
            expertiseText.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(expertiseText);
        }
        
        
        
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel);

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog dialog = changePasswordWindow(userId);
                dialog.setVisible(true);
            }
        });


        changePasswordButton.setPreferredSize(new Dimension(150, 50));

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(changePasswordButton);

        JButton changeUsernameButton = new JButton("Change Username");
        changeUsernameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog dialog = changeUsernameWindow(userId);
                dialog.setVisible(true);
            }
        });


        changeUsernameButton.setPreferredSize(new Dimension(150, 50));
        buttonPanel.add(changeUsernameButton);



	}

    public JDialog changePasswordWindow(int userId) {
        JDialog changePasswordDialog= new JDialog(new JFrame(), "Change Password", true);
        changePasswordDialog.setSize(400, 200);
        changePasswordDialog.setResizable(false);
        changePasswordDialog.setLocationRelativeTo(null);
        changePasswordDialog.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        changePasswordDialog.getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel oldpasswordLabel = new JLabel("Old password");
        oldpasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        oldpasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(oldpasswordLabel);

        JPasswordField oldpasswordField = new JPasswordField();
        oldpasswordField.setColumns(10);
        panel.add(oldpasswordField);

        JLabel newpasswordLabel = new JLabel("New password");
        newpasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newpasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(newpasswordLabel);

        JPasswordField newpasswordField = new JPasswordField();
        newpasswordField.setColumns(10);
        panel.add(newpasswordField);

        JLabel confnewpasswordLabel = new JLabel("Confirm new password");
        confnewpasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        confnewpasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(confnewpasswordLabel);

        JPasswordField confnewpasswordField = new JPasswordField();
        confnewpasswordField.setColumns(10);
        panel.add(confnewpasswordField);

        JPanel panel_2 = new JPanel();
        changePasswordDialog.getContentPane().add(panel_2, BorderLayout.SOUTH);

        JButton confirmPasswordButton = new JButton("Change Password");
        confirmPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newPass1 = new String(newpasswordField.getPassword());
                String newPass2 = new String(confnewpasswordField.getPassword());
                String oldPassword = new String(oldpasswordField.getPassword());
                int userType = LoginController.getAccountType(userId);
                    if (oldPassword.equals(UserController.getDoctor(userId).password)){
                        if (newPass1.equals(newPass2)){
                            if(UserController.changePassword(userId,newPass1)){
                                JOptionPane.showMessageDialog(new JFrame(), "Password is changed successfully");
                                changePasswordDialog.dispose();
                                changePanel(parentFrame,new ProfilePage(parentFrame,userId));

                            }
                            else{
                                JOptionPane.showMessageDialog(new JFrame(), "Requirements not met!!!");
                            }

                        }else{
                            JOptionPane.showMessageDialog(new JFrame(), "Passwords not matching!!!");
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Old password is wrong!!!");
                    }



            }
        });
        panel_2.add(confirmPasswordButton);

        return changePasswordDialog;

    }


    public JDialog changeUsernameWindow(int userId) {
        JDialog changeUsernameDialog= new JDialog(new JFrame(), "Change Username", true);
        changeUsernameDialog.setSize(400, 200);
        changeUsernameDialog.setResizable(false);
        changeUsernameDialog.setLocationRelativeTo(null);
        changeUsernameDialog.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        changeUsernameDialog.getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel oldpasswordLabel = new JLabel("Password");
        oldpasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        oldpasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(oldpasswordLabel);

        JPasswordField oldpasswordField = new JPasswordField();
        oldpasswordField.setColumns(10);
        panel.add(oldpasswordField);

        JLabel newUsernameLabel = new JLabel("New username");
        newUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newUsernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(newUsernameLabel);

        JTextField newUsernameField = new JTextField();
        newUsernameField.setColumns(10);
        panel.add(newUsernameField);

        JLabel confnewUsernameLabel = new JLabel("Confirm new username");
        confnewUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        confnewUsernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(confnewUsernameLabel);

        JTextField confnewusernameField = new JTextField();
        confnewusernameField.setColumns(10);
        panel.add(confnewusernameField);

        JPanel panel_2 = new JPanel();
        changeUsernameDialog.getContentPane().add(panel_2, BorderLayout.SOUTH);
        JButton confirmUsernameButton = new JButton("Change Username");
        confirmUsernameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newUsername1 = newUsernameField.getText();
                String newUsername2 =confnewusernameField.getText();
                String oldPassword = new String(oldpasswordField.getPassword());
                int userType = LoginController.getAccountType(userId);
                if (oldPassword.equals(UserController.getDoctor(userId).password)){
                    if (newUsername1.equals(newUsername2)){
                        if(UserController.changeUsername(userId,newUsername1)){
                            JOptionPane.showMessageDialog(new JFrame(), "Username is changed successfully");
                            changeUsernameDialog.dispose();
                            changePanel(parentFrame,new ProfilePage(parentFrame,userId));

                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Requirements not met!!!");
                        }

                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Usernames not matching!!!");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Old password is wrong!!!");
                }



            }
        });
        panel_2.add(confirmUsernameButton);

        return changeUsernameDialog;

    }
    public void  changePanel(JFrame frame,JPanel newPanel ) {

        frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
        frame.getContentPane().add(newPanel, BorderLayout.CENTER);
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();
    }

}
