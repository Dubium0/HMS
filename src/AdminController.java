import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminController {

    public static ArrayList<ArrayList<String>> getPatientNumberbyDepartment(){
        Connection myConn = DBConnection.getConnection();
        String query = "Select count(appointmentDate) as patientCount, departmentName from (SELECT appointmentDate,appointment.patientId,appointment.doctorId,departmentId FROM appointment,booking,doctor,expertise where  doctor.expertiseId = expertise.expertiseId and doctor.doctorId = appointment.doctorId) as temp right join department on department.departmentid = temp.departmentId group by departmentName ;";
        ArrayList<ArrayList<String>> array_list = new ArrayList<>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String dept_name = rs.getString("departmentName");
                String patientCount = String.valueOf(rs.getInt(1));
                ArrayList<String> temp = new ArrayList<>();
                temp.add(dept_name);
                temp.add(patientCount);
                array_list.add(temp);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  array_list;
    }

    public static ArrayList<ArrayList<String>> getPatientByTime() {
        Connection myConn = DBConnection.getConnection();
        String query = "Select Date(appointmentDate),count(appointmentdate) from appointment  group by Date(appointmentDate)";
        ArrayList<ArrayList<String>> array_list = new ArrayList<>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String date = rs.getString(1);
                String count = String.valueOf(rs.getInt(2));
                ArrayList<String> temp = new ArrayList<>();
                temp.add(date);
                temp.add(count);
                array_list.add(temp);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  array_list;
    }

    public  static  double getRatioOfAppointmentsAndBookingsForDepartment(String departmentName){
        String query  = "select numberOfAppointments, numberOfBookings\n" +
                "from department_appointments\n" +
                "where department_appointments.departmentName = ?;";


        Connection myConnection  = DBConnection.getConnection();
        try {
            PreparedStatement stmt = myConnection.prepareStatement(query);
            stmt.setString(1,departmentName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int n_app = rs.getInt("numberOfAppointments");
                int n_booking = rs.getInt("numberOfBookings");
                return (double)n_app / (double)n_booking;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  0;
    }


    public  static Dictionary<String,Integer> getDepartmentsMostBookedRooms(){
        Dictionary<String,Integer> dictionary = new Hashtable<>();
        String query = "select * \n" +
                "from departments_booked_room_counts;\n";
        Connection myConn = DBConnection.getConnection();

        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String departmentName =  rs.getString("departmentName");
                Integer roomID  = rs.getInt("");
                dictionary.put(departmentName,roomID);
            }

        }catch (SQLException e ){
            e.printStackTrace();
        }


        return dictionary;

    }

    public static Department getBookingDepartment(int bookingID){
        String query  =" select departmentName\n" +
                "from booking_stats\n" +
                "where bookingID   =  ?;";
        Connection myConn = DBConnection.getConnection();
        Department dp = null;
        try{
            PreparedStatement stmt  = myConn.prepareStatement(query);
            stmt.setInt(1,bookingID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dp = new Department(rs.getString("departmentName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  dp;
    }
    // DONE : CAN ADD NURSE
    // DONE : CAN ADD ADMIN
    // DONE : CAN ADD DOCTOR

    // göz kanatan kod satırları geliyor

}
