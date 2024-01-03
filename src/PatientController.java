import  java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    public static ArrayList<Patient> getPatients(){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * FROM patient_view;";
        ArrayList<Patient> patients = new ArrayList<Patient>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String name_surname = rs.getString("name_surname");
                int user_id = rs.getInt("userID");
                int age = rs.getInt("age");
                String user_name = rs.getString("user_name");
                String gender =rs.getString("gender");
                int password = rs.getInt("password_");
                Patient  patient=  new Patient(name_surname,age,user_name,gender,password );
                patient.user_id = user_id;
                patients.add(patient);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  patients;
    }

    public static Patient getPatient(int id){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM patient_view where  patient_view.userID = ?";
        Patient patient = null;
        try {
            PreparedStatement stmt  =myConn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs  =stmt.executeQuery();
            rs.next();
            int user_id   = rs.getInt("userID");
            String name_surname = rs.getString("name_surname");
            int age = rs.getInt("age");
            String user_name = rs.getString("user_name");
            String gender =rs.getString("gender");
            int password = rs.getInt("password_");
            patient = new Patient(name_surname,age,user_name,gender,password );
            patient.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  patient;
    }
    public static Patient getPatient(String user_name){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM patient_view where  patient_view.user_name = ?";
        Patient patient = null;
        try {
            PreparedStatement stmt  =myConn.prepareStatement(query);
            stmt.setString(1,user_name);
            ResultSet rs  =stmt.executeQuery();
            rs.next();
            int user_id   = rs.getInt("userID");
            String name_surname = rs.getString("name_surname");
            int age = rs.getInt("age");
            String user_name_ = rs.getString("user_name");
            String gender =rs.getString("gender");
            int password = rs.getInt("password_");
            patient = new Patient(name_surname,age,user_name_,gender,password);
            patient.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  patient;
    }


    public  static  boolean addPatient(Patient patient){
        Connection myConn = DBConnection.getConnection();

        String query_1 = "INSERT INTO USER (name_surname,user_name, age, password_, gender) VALUES( ? , ?, ?, ?, ?); ";
        int user_id  = -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query_1,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,patient.name_surname);
            stmt.setString(2,patient.user_name);
            stmt.setInt(3,patient.age);
            stmt.setInt(4,patient.password);
            stmt.setString(5,patient.gender);
            int r = stmt.executeUpdate();
            if(r >0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    user_id = rs.getInt(1);
                }


            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
        if(user_id > -1){
            String query_2 = "INSERT INTO PATIENT (patientID) VALUES ( ? )";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2);
                stmt.setInt(1,user_id);
                int r  = stmt.executeUpdate();
                if(r>0) return  true;
            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        return false;
    }



}
