import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private boolean isLoggedIn;

    public LoginController(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String password) {
        try {
            preparedStatement = connection.prepareStatement(
                    "(SELECT * FROM user WHERE user_name = ?)");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (!password.equals(resultSet.getString("password"))) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password is incorrect!");
                    return false;
                } else {
                    this.isLoggedIn = true;
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "User does not exist!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public int getAccountType(int userId) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE patientId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return RegisterController.PATIENT;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM doctor WHERE doctorId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return RegisterController.DOCTOR;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM nurse WHERE nurseId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return RegisterController.NURSE;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE adminId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return RegisterController.NURSE;
            }
            return -1;



        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
