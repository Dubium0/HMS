import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
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
                String password = rs.getString("password_");
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
            String password = rs.getString("password_");
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
            String password = rs.getString("password_");;
            patient = new Patient(name_surname,age,user_name_,gender,password);
            patient.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  patient;
    }
    public  static  int addPatient(Patient patient){
        Connection myConn = DBConnection.getConnection();

        String query_1 = "INSERT INTO USER (name_surname,user_name, age, password_, gender) VALUES( ? , ?, ?, ?, ?); ";
        int user_id  = -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query_1,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,patient.name_surname);
            stmt.setString(2,patient.user_name);
            stmt.setInt(3,patient.age);
            stmt.setString(4,patient.password);
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

            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        return user_id;
    }



    public static ArrayList<Nurse> getNurses(){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * FROM nurse_view;";
        ArrayList<Nurse> nurses = new ArrayList<Nurse>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String name_surname = rs.getString("name_surname");
                int user_id = rs.getInt("userID");
                int age = rs.getInt("age");
                String user_name = rs.getString("user_name");
                String gender =rs.getString("gender");
                String password = rs.getString("password_");
                Nurse  nurse=  new Nurse(name_surname,age,user_name,gender,password );
                nurse.user_id = user_id;
                nurses.add(nurse);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurses;
    }
    public static Nurse getNurse(int id){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM nurse_view where  nurse_view.userID = ?";
        Nurse nurse = null;
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
            String password = rs.getString("password_");
            nurse = new Nurse(name_surname,age,user_name,gender,password );
            nurse.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurse;
    }
    public static Nurse getNurse(String user_name){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM nurse_view where  nurse_view.user_name = ?";
        Nurse nurse = null;
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
            String password = rs.getString("password_");
            nurse = new Nurse(name_surname,age,user_name_,gender,password);
            nurse.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurse;
    }
    public  static  int addNurse(Nurse nurse){
        Connection myConn = DBConnection.getConnection();

        String query_1 = "INSERT INTO USER (name_surname,user_name, age, password_, gender) VALUES( ? , ?, ?, ?, ?); ";
        int user_id  = -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query_1,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,nurse.name_surname);
            stmt.setString(2,nurse.user_name);
            stmt.setInt(3,nurse.age);
            stmt.setString(4,nurse.password);
            stmt.setString(5,nurse.gender);
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
            String query_2 = "INSERT INTO NURSE (nurseID) VALUES ( ? )";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2);
                stmt.setInt(1,user_id);
                int r  = stmt.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        return user_id;
    }



    public static ArrayList<Admin> getAdmins(){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * FROM admin_view;";
        ArrayList<Admin> admins = new ArrayList<Admin>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String name_surname = rs.getString("name_surname");
                int user_id = rs.getInt("userID");
                int age = rs.getInt("age");
                String user_name = rs.getString("user_name");
                String gender =rs.getString("gender");
                String password = rs.getString("password_");
                Admin  admin=  new Admin(name_surname,age,user_name,gender,password );
                admin.user_id = user_id;
                admins.add(admin);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  admins;
    }
    public static Admin getAdmin(int id){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM admin_view where  admin_view.userID = ?";
        Admin admin = null;
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
            String password = rs.getString("password_");
            admin = new Admin(name_surname,age,user_name,gender,password );
            admin.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  admin;
    }
    public static Admin getAdmin(String user_name){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM admin_view where  admin_view.user_name = ?";
        Admin admin = null;
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
            String password = rs.getString("password_");
            admin = new Admin(name_surname,age,user_name_,gender,password);
            admin.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  admin;
    }
    public  static  int addAdmin(Admin admin){
        Connection myConn = DBConnection.getConnection();

        String query_1 = "INSERT INTO USER (name_surname,user_name, age, password_, gender) VALUES( ? , ?, ?, ?, ?); ";
        int user_id  = -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query_1,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,admin.name_surname);
            stmt.setString(2,admin.user_name);
            stmt.setInt(3,admin.age);
            stmt.setString(4,admin.password);
            stmt.setString(5,admin.gender);
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
            String query_2 = "INSERT INTO ADMIN (adminID) VALUES ( ? )";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2);
                stmt.setInt(1,user_id);
                int r  = stmt.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        return user_id;
    }



    public static ArrayList<Doctor> getDoctors(){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * FROM doctor_view;";
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String name_surname = rs.getString("name_surname");
                int user_id = rs.getInt("userID");
                int age = rs.getInt("age");
                int expertise_id = rs.getInt("expertiseID");
                String user_name = rs.getString("user_name");
                String gender =rs.getString("gender");
                String password = rs.getString("password_");
                Doctor  doctor=  new Doctor(name_surname,age,user_name,gender,expertise_id,password );
                doctor.user_id = user_id;
                doctors.add(doctor);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  doctors;
    }
    public static Doctor getDoctor(int id){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM doctor_view where  doctor_view.userID = ?";
        Doctor doctor = null;
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
            int expertise_id = rs.getInt("expertiseID");
            String password = rs.getString("password_");
            doctor = new Doctor(name_surname,age,user_name,gender, expertise_id,password );
            doctor.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  doctor;
    }
    public static Doctor getDoctor(String user_name){
        Connection myConn = DBConnection.getConnection();
        String query = "SELECT * FROM doctor_view where  doctor_view.user_name = ?";
        Doctor doctor = null;
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
            int expertise_id = rs.getInt("expertiseID");
            String password = rs.getString("password_");
            doctor = new Doctor(name_surname,age,user_name,gender, expertise_id,password );
            doctor.user_id = user_id;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  doctor;
    }
    public  static  int addDoctor(Doctor doctor){
        Connection myConn = DBConnection.getConnection();

        String query_1 = "INSERT INTO USER (name_surname,user_name, age, password_, gender) VALUES( ? , ?, ?, ?, ?); ";
        int user_id  = -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query_1,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,doctor.name_surname);
            stmt.setString(2,doctor.user_name);
            stmt.setInt(3,doctor.age);
            stmt.setString(4,doctor.password);
            stmt.setString(5,doctor.gender);
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
            String query_2 = "INSERT INTO DOCTOR (doctorId,expertiseID) VALUES ( ? , ? )";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2);
                stmt.setInt(1,user_id);
                stmt.setInt(2,doctor.expertise_id);
                int r  = stmt.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        return user_id;
    }

    public static User getUser(int userId){
        Connection myConn = DBConnection.getConnection();
        int userType = LoginController.getAccountType(userId);
        String query = "SELECT * FROM user where  user.userId = ?";
        User user = null;
        if (userType == LoginController.PATIENT){
            user = UserController.getPatient(userId);
        }
        else if (userType == LoginController.DOCTOR){
            user = UserController.getDoctor(userId);
        }
        else if (userType == LoginController.NURSE){
            user = UserController.getNurse(userId);
        }
        else if (userType == LoginController.ADMIN){
            user = UserController.getAdmin(userId);
        }

        return  user;
    }



}
