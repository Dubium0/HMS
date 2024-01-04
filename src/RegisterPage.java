import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class RegisterPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegisterPage() {
        initialize();
    }

    private void initialize() {
        setResizable(false);
        setBackground(Color.WHITE);
        setTitle("Register Page");
        setBounds(100, 100, 428, 341);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("HMS Register");
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel panelNorth = new JPanel();
        panelNorth.add(headerLabel);
        contentPane.add(panelNorth, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        GridLayout gl_fieldsPanel = new GridLayout(0, 2);
        gl_fieldsPanel.setHgap(5);
        gl_fieldsPanel.setVgap(5);
        fieldsPanel.setLayout(gl_fieldsPanel);
        
        
        //username
        JLabel usernameText = new JLabel("Username:");
        usernameText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(usernameText);
        JTextField usernameField = new JTextField(20);
        fieldsPanel.add(usernameField);

        //password
        JLabel passwordText = new JLabel("Password:");
        passwordText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(passwordText);
        JTextField passwordField = new JPasswordField();
        fieldsPanel.add(passwordField);
        
        //name
        JLabel nameText = new JLabel("Name:");
        nameText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(nameText);
        JTextField nameField = new JTextField(20);
        fieldsPanel.add(nameField);
        
        
        //surname
        JLabel surnameText = new JLabel("Surname:");
        surnameText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(surnameText);
        JTextField surnameField = new JTextField(20);
        fieldsPanel.add(surnameField);
        
        //age
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#0"));
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);

        
        JLabel ageText = new JLabel("Age:");
        ageText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(ageText);
        JFormattedTextField ageField = new JFormattedTextField(formatter);
        fieldsPanel.add(ageField);
        ageField.setValue(null);
        
        
        
        //gender
        JLabel genderText = new JLabel("Gender:");
        genderText.setHorizontalAlignment(SwingConstants.LEFT);
        fieldsPanel.add(genderText);
        JComboBox genderField = new JComboBox();
        genderField.setModel(new DefaultComboBoxModel(new String[] {"Not Specified", "Male", "Female"}));
        fieldsPanel.add(genderField);

        panelCenter.add(fieldsPanel, BorderLayout.CENTER);

        contentPane.add(panelCenter, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel();
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }

        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                try {
                    LoginPage loginPage = new LoginPage();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        panelSouth.add(registerButton);
        panelSouth.add(cancelButton);

        contentPane.add(panelSouth, BorderLayout.SOUTH);

        setVisible(true);
    }
}
