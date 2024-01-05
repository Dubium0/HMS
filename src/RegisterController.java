
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisterController {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private AccountValidator accountValidator;

    public static final int PATIENT = 0;
    public static final int DOCTOR = 1;
    public static final int NURSE = 2;
    public static final int ADMIN = 3;

    public RegisterController(Connection connection) {
        this.connection = connection;
        this.accountValidator = new AccountValidator(connection);
    }

    public boolean register(String name_surname,String username, int age,String password,String gender) {
        try {
            if (!accountValidator.validateUsername(username)) {
                JOptionPane.showMessageDialog(new JFrame(), "Username already exists!");
                return false;
            } else if (!accountValidator.validatePassword(password)) {
                JOptionPane.showMessageDialog(new JFrame(), "Password must be at least 6 characters!");
                return false;
            } else {
                preparedStatement = connection.prepareStatement("INSERT INTO user (name_surname,user_name,age, password,gender) VALUES (?,?,?,?,?)");

                preparedStatement.setString(1, name_surname);
                preparedStatement.setString(2, username);
                preparedStatement.setInt(3, age);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, gender);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(new JFrame(), "Account Created!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
