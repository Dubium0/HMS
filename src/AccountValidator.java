
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountValidator {



    public static boolean validateUsername(String username) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "(SELECT * FROM user WHERE user_name = ?) ");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 6;
    }
    public static boolean validateAge(int age){return age >0;}
}
