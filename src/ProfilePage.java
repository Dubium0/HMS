import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ProfilePage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private int userId;
	/**
	 * Create the panel.
	 */
	public ProfilePage(int userId) {
		
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
        
        changePasswordButton.setPreferredSize(new Dimension(150, 50));

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(changePasswordButton);

        JButton changeUsernameButton = new JButton("Change Username");

        changeUsernameButton.setPreferredSize(new Dimension(150, 50));
        buttonPanel.add(changeUsernameButton);



	}

}
