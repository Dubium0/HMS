
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisterController {






    public static boolean register(String name_surname,String username, int age,String password,String gender) {
        Connection connection = DBConnection.getConnection();
        try {
            if (!AccountValidator.validateUsername(username)) {
                JOptionPane.showMessageDialog(new JFrame(), "Username already exists!");
                return false;
            } else if (!AccountValidator.validatePassword(password)) {
                JOptionPane.showMessageDialog(new JFrame(), "Password must be at least 6 characters!");
                return false;
            }
            else if(!AccountValidator.validateAge(age)){
                JOptionPane.showMessageDialog(new JFrame(), "Invalid Age");
                return false;
            }
            else {
                if (UserController.addPatient(new Patient(name_surname,age,username,gender,password)) != -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Account Created!");
                    return true;
                }
                else{
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
