import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {
	
	
	private int userId;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow(int userId) {
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1200, 500, 2800, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setResizable(false);
        
        JPanel menuPanel = new JPanel();
        frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        menuPanel.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel menuTextPanel1 = new JPanel();
        menuPanel.add(menuTextPanel1);
        menuTextPanel1.setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel helloText = new JLabel("Hello " + UserController.getUser(userId).user_name);
        helloText.setPreferredSize(new Dimension(0, 50));
        helloText.setHorizontalAlignment(SwingConstants.CENTER);
        helloText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        menuTextPanel1.add(helloText);
        
        JPanel menuButtonPanel = new JPanel();
        menuPanel.add(menuButtonPanel);
        menuButtonPanel.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton homeButton = new JButton("Home");
        homeButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		goHomePage();
        	}
        });
        homeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        menuButtonPanel.add(homeButton);
        
        
        JButton profileButton = new JButton("Profile");
        profileButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		changePanel(frame,new ProfilePage(userId));
        	}
        });
        profileButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        menuButtonPanel.add(profileButton);
        
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		new LoginPage();
        	}
        });
        logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        menuButtonPanel.add(logoutButton);
        
        
        JButton exitButton = new JButton("Exit");
        exitButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);        	}
        });
        exitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        menuButtonPanel.add(exitButton);
        
        
        JPanel menuTextPanel2 = new JPanel();
        menuPanel.add(menuTextPanel2);
        menuTextPanel2.setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel hsmText = new JLabel("Hospital Manegement System");
        hsmText.setHorizontalAlignment(SwingConstants.CENTER);
        hsmText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        menuTextPanel2.add(hsmText);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        
		// add home panels
		
        goHomePage();
		
		frame.setVisible(true);
		
		
	}
	
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}
	
	private void goHomePage() {
		int userType = LoginController.getAccountType(userId);
		System.out.println(userType);
		if (userType == LoginController.PATIENT) {
			changePanel(frame,new PatientMainPage(frame));
			
		}
		else if (userType == LoginController.ADMIN) {
			changePanel(frame,new AdminMainPage(frame,userId));
		}
		else if (userType == LoginController.DOCTOR) {
			changePanel(frame,new DoctorMainPage(frame,userId));
		}
		else if (userType == LoginController.NURSE) {
			changePanel(frame,new NurseMainPage(frame,userId));
		}
		
	}
}
