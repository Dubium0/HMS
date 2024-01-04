import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;



import javax.swing.JComboBox;

public class LoginPage extends JFrame{

    private static final long serialVersionUID = 1L;

    public JFrame frmLoginpage;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage window = new LoginPage();
                    window.frmLoginpage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public LoginPage() {
        initialize();
    }

    private void initialize() {

        frmLoginpage = new JFrame();
        frmLoginpage.setResizable(false);
        frmLoginpage.setBackground(Color.WHITE);
        frmLoginpage.setTitle("LoginPage");
        frmLoginpage.setBounds(100, 100, 307, 452);
        frmLoginpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLoginpage.getContentPane().setLayout(new BorderLayout(0, 0));
        frmLoginpage.setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel();
        frmLoginpage.getContentPane().add(titlePanel, BorderLayout.NORTH);

        JLabel lblNewLabel_3 = new JLabel("HMS Login");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 30));
        titlePanel.add(lblNewLabel_3);

        JPanel panel = new JPanel();
        frmLoginpage.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_1 = new JPanel();
        centerPanel.add(panel_1);

        JPanel panelText = new JPanel();
        centerPanel.add(panelText);

        JLabel lblNewLabel = new JLabel("WELCOME");
        panelText.add(lblNewLabel);

        JPanel panel_2 = new JPanel();
        centerPanel.add(panel_2);

        JPanel panelUsername = new JPanel();
        centerPanel.add(panelUsername);

        JLabel lblNewLabel_1 = new JLabel("Username: ");
        panelUsername.add(lblNewLabel_1);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        panelUsername.add(usernameField);



        JPanel panel_4 = new JPanel();
        centerPanel.add(panel_4);

        JPanel panelPassword = new JPanel();
        centerPanel.add(panelPassword);

        JLabel lblNewLabel_2 = new JLabel("Password: ");
        panelPassword.add(lblNewLabel_2);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        panelPassword.add(passwordField);

        JPanel panel_6 = new JPanel();
        centerPanel.add(panel_6);

        JPanel panelMessage = new JPanel();
        centerPanel.add(panelMessage);


        JPanel panelButton = new JPanel();
        centerPanel.add(panelButton);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {	
            	String userType = usernameField.getText();
            	System.out.println(userType);
            	if (userType.equals("1")) {
                	System.out.println("true");
                	frmLoginpage.dispose();
            		new MainWindow(1);
            	}
            	else if (userType.equals("2")) {
                	frmLoginpage.dispose();
            		new MainWindow(2);
            	}
            	else if (userType.equals("3")) {
                	frmLoginpage.dispose();
            		new MainWindow(3);
            	}
            	else {
                	frmLoginpage.dispose();
                	new MainWindow(4);
            	}
            }





        });
        panelButton.add(btnNewButton);
        panel.add(centerPanel);

        JButton btnNewButtonRegister = new JButton("Register");
        btnNewButtonRegister.setHorizontalAlignment(SwingConstants.RIGHT);

        
        btnNewButtonRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	frmLoginpage.dispose();
                RegisterPage registerPage = new RegisterPage();
            }
        });


        panelButton.add(btnNewButtonRegister);

        frmLoginpage.setVisible(true);
    }
}