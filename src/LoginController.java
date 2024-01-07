import com.mysql.cj.log.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {
    public static final int PATIENT = 0;
    public static final int DOCTOR = 1;
    public static final int NURSE = 2;
    public static final int ADMIN = 3;


    public static int login(String username, String password) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "(SELECT * FROM user WHERE user_name = ?)");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (!password.equals(resultSet.getString("password_"))) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password is incorrect!");
                    return -1;
                } else {
                    return resultSet.getInt("userId");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "User does not exist!");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public static int getAccountType(int userId) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE patientId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return LoginController.PATIENT;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM doctor WHERE doctorId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return LoginController.DOCTOR;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM nurse WHERE nurseId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return LoginController.NURSE;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE adminId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return LoginController.ADMIN;
            }
            return -1;



        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
