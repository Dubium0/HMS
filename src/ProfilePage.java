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
		
				
        this.setLayout(new GridLayout(0, 1, 0, 20));

        JLabel userIdText = new JLabel("userId " );
        userIdText.setFont(new Font("Tahoma", Font.BOLD, 31));
        userIdText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(userIdText);

        JLabel usernameText = new JLabel("Username");
        usernameText.setFont(new Font("Tahoma", Font.BOLD, 31));
        usernameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(usernameText);
        
        JLabel nameText = new JLabel("Name " );
        nameText.setFont(new Font("Tahoma", Font.BOLD, 31));
        nameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(nameText);

        
        JLabel ageText = new JLabel("Age " );
        ageText.setHorizontalAlignment(SwingConstants.CENTER);
        ageText.setFont(new Font("Tahoma", Font.BOLD, 31));
        this.add(ageText);
        
        JLabel genderText = new JLabel("Gender " );
        genderText.setFont(new Font("Tahoma", Font.BOLD, 31));
        genderText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(genderText);



        if (userId == 3) {  // if it is doctor
        	JLabel departmentText = new JLabel("Department");
        	departmentText.setFont(new Font("Tahoma", Font.BOLD, 31));
        	departmentText.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(departmentText);
            
            JLabel expertiseText = new JLabel("Expertise");
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
