import javax.print.Doc;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.time.LocalDate;

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
            stmt.setInt(4,patient.password.hashCode());
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
    public  static boolean deleteNurse(int nurseID){
        String query=  "delete from NURSE\n" +
                "where NURSE.nurseID =   ? ;";

        Connection myConn = DBConnection.getConnection();
        boolean result = false;
        try{
            PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,nurseID);
            int r  = stmt.executeUpdate();
            if(r>0){
                ResultSet rs = stmt.getResultSet();
                result =   true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            result =  false;
        }
        return result;
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
            stmt.setInt(4,nurse.password.hashCode());
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
            stmt.setInt(4,admin.password.hashCode());
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
            stmt.setInt(4,doctor.password.hashCode());
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

    public static boolean changeUsername(int userId, String newUsername) {
        Connection myConn = DBConnection.getConnection();
        try {
            if (!AccountValidator.validateUsername(newUsername)) {
                return false;
            } else {
                PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE user SET user_name = ? WHERE userid = ?");
                preparedStatement.setString(1, newUsername);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changePassword(int userId, String newPassword) {
        Connection myConn = DBConnection.getConnection();
        try {
            if (!AccountValidator.validatePassword(newPassword)) {
                return false;
            } else {
                PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE user SET password_ = ? WHERE userid = ?");
                preparedStatement.setString(1, newPassword);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Doctor> getFilteredDoctors(String expertiseName,int minDay, int maxDay, int minHour, int maxHour){
        Connection myConn = DBConnection.getConnection();
            String query  = "SELECT *\n" +
                    "FROM doctor_view natural join EXPERTISE\n" +
                    "WHERE EXPERTISE.expertiseName = ? \n" +
                    "  AND (\n" +
                    "        doctor_view.userID IN (\n" +
                    "            SELECT doctor_view.userID\n" +
                    "            FROM DOCTOR_AVAILABILITY\n" +
                    "            WHERE doctor_view.userID = DOCTOR_AVAILABILITY.doctorID\n" +
                    "              AND DATE(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                    "              AND DATE(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                    "              AND TIME(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                    "              AND TIME(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                    "            GROUP BY doctor_view.userID\n" +
                    "            HAVING COUNT(doctor_view.userID) < ?\n" +
                    "        )\n" +
                    "        OR\n" +
                    "        doctor_view.userID IN (\n" +
                    "            SELECT doctor_view.userID\n" +
                    "            FROM DOCTOR_AVAILABILITY\n" +
                    "            JOIN doctor_view ON doctor_view.userID = DOCTOR_AVAILABILITY.doctorID\n" +
                    "            WHERE DOCTOR_AVAILABILITY.availability = 1\n" +
                    "              AND DATE(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                    "              AND DATE(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                    "              AND TIME(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                    "              AND TIME(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                    "        )\n" +
                    "    );";

        String query_without_expertise  ="SELECT *\n" +
                "FROM doctor_view\n" +
                "WHERE \t\tdoctor_view.userID IN (\n" +
                "            SELECT doctor_view.userID\n" +
                "            FROM DOCTOR_AVAILABILITY\n" +
                "            WHERE doctor_view.userID = DOCTOR_AVAILABILITY.doctorID\n" +
                "              AND DATE(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                "              AND DATE(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                "              AND TIME(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                "              AND TIME(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                "            GROUP BY doctor_view.userID\n" +
                "            HAVING COUNT(doctor_view.userID) < ?\n" +
                "        )\n" +
                "        OR\n" +
                "        doctor_view.userID IN (\n" +
                "            SELECT doctor_view.userID\n" +
                "            FROM DOCTOR_AVAILABILITY\n" +
                "            JOIN doctor_view ON doctor_view.userID = DOCTOR_AVAILABILITY.doctorID\n" +
                "            WHERE DOCTOR_AVAILABILITY.availability = 1\n" +
                "              AND DATE(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                "              AND DATE(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                "              AND TIME(DOCTOR_AVAILABILITY.date_) >= ?\n" +
                "              AND TIME(DOCTOR_AVAILABILITY.date_) <= ?\n" +
                "        );";

        LocalDate currentDate = LocalDate.now();
        LocalDate minDate=  currentDate.plusDays(minDay);
        LocalDate maxDate= currentDate.plusDays(maxDay);



        String minHour_STR;
        String maxHour_STR;


        if(minHour < 10){
            minHour_STR = "0"+ minHour + ":00:00";
        }else{
            minHour_STR = minHour + ":00:00";
        }

        if(maxHour < 10){
            maxHour_STR = "0"+ maxHour + ":00:00";
        }else{
            maxHour_STR =  maxHour + ":00:00";
        }




        ArrayList<Doctor> doctors = new ArrayList< Doctor >();


        try{


            Date minDate_ = Date.valueOf(minDate);
            Date maxDate_ = Date.valueOf(maxDate);
            Time minTime = Time.valueOf(minHour_STR);
            Time maxTime = Time.valueOf(maxHour_STR);
            PreparedStatement stmt;
            if(expertiseName == null){
                stmt = myConn.prepareStatement(query_without_expertise);

                stmt.setDate(1,minDate_);
                stmt.setDate(2,maxDate_);
                stmt.setTime(3,minTime);
                stmt.setTime(4,maxTime);
                stmt.setInt(5,maxHour-minHour);
                stmt.setDate(6,minDate_);
                stmt.setDate(7,maxDate_);
                stmt.setTime(8,minTime);
                stmt.setTime(9,maxTime);
            }
            else{
                stmt = myConn.prepareStatement(query);

                stmt.setString(1,expertiseName);
                stmt.setDate(2,minDate_);
                stmt.setDate(3,maxDate_);
                stmt.setTime(4,minTime);
                stmt.setTime(5,maxTime);
                stmt.setInt(6,maxHour-minHour);
                stmt.setDate(7,minDate_);
                stmt.setDate(8,maxDate_);
                stmt.setTime(9,minTime);
                stmt.setTime(10,maxTime);

            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int password= rs.getInt("password_");

                Doctor doctor = new Doctor(rs.getString("name_surname"),rs.getInt("age"),rs.getString("user_name"),
                        rs.getString("gender"), rs.getInt("expertiseID"),String.valueOf(password));
                doctor.user_id = rs.getInt("userID");
                doctors.add(doctor);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return  doctors;

    };


}
