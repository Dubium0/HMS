import  java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    public  static  boolean addAppointment(Appointment appointment){
        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO APPOINTMENT (appointmentDate, patientID, doctorID) VALUES(?, ? ,? );";

        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setTimestamp(1,appointment.date);
            stmt.setInt(2,appointment.patient_id);
            stmt.setInt(3,appointment.doctor_id);

            int r = stmt.executeUpdate();
            if(r>0){
                return  true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return  false;
    }
    public  static  boolean deleteAppointment(Appointment appointment){
        Connection myConn = DBConnection.getConnection();
        String query = "DELETE FROM APPOINTMENT where APPOINTMENT.patientID = ? and APPOINTMENT.doctorID =  ?  and  APPOINTMENT.appointmentDate =  ? ;";
        LocalDateTime currentDate= LocalDateTime.now();
        Instant timestamp = Instant.ofEpochMilli(appointment.date.getTime());
        Instant currentTime = Instant.now();
        Duration duration = Duration.between(currentTime,timestamp);
        //System.out.println(duration.toHours());
        if(duration.toHours() > 24 ){


            try {
                PreparedStatement stmt = myConn.prepareStatement(query);
                stmt.setInt(1,appointment.patient_id);
                stmt.setInt(2,appointment.doctor_id);

                stmt.setTimestamp(3,appointment.date);

                int r = stmt.executeUpdate();
                if(r>0){
                    System.out.println("appoinment deleted");

                    if(appointment.booking_id >0){
                        return DoctorController.deleteBooking(appointment.booking_id, appointment.date);
                    }else{
                        return  true;
                    }



                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return  false;

    }
    public  static  ArrayList<Appointment> getAppointments(int patient_id,Date date){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.appointmentDate = ?  and appointment.patientID = ?";

        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setDate(1,date);
            stmt.setInt(2,patient_id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Appointment appointment = new Appointment(rs.getTimestamp(1),rs.getInt(2),rs.getInt(2));
                appointment.booking_id  = rs.getInt(4);
                appointments.add(appointment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  appointments;
    }
    public  static  ArrayList<Appointment> getAppointments(int patient_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.patientID = ?";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,patient_id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Appointment appointment = new Appointment(rs.getTimestamp(1),rs.getInt(2),rs.getInt(3));
                appointment.booking_id  = rs.getInt(4);
                appointments.add(appointment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  appointments;
    }
    public  static  Appointment getAppointment(int patient_id,int doctor_id , Timestamp date){

        Connection myConn = DBConnection.getConnection();
        Appointment appointment = null;
        String query  =  "SELECT *  FROM APPOINTMENT where APPOINTMENT.patientID = ? and APPOINTMENT.doctorID =  ?  and  APPOINTMENT.appointmentDate =  ? ;";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,patient_id);
            stmt.setInt(2,doctor_id);
            stmt.setTimestamp(3,date);
            ResultSet rs= stmt.executeQuery();
            if(rs.next()){
                appointment  = new Appointment(date,patient_id,doctor_id);
                appointment.booking_id  = rs.getInt(4);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  appointment;

    }

    public static ArrayList<Appointment> getUpcomingAppointment(int patient_id){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.patientID = ? and appointment.appointmentDate > NOW()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,patient_id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Appointment appointment = new Appointment(rs.getTimestamp(1),rs.getInt(2),rs.getInt(3));
                appointment.booking_id  = rs.getInt(4);
                appointments.add(appointment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  appointments;
    }

    public static ArrayList<Appointment> getPastAppointment(int patient_id){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.patientID = ? and appointment.appointmentDate < NOW()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,patient_id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Appointment appointment = new Appointment(rs.getTimestamp(1),rs.getInt(2),rs.getInt(3));
                appointment.booking_id  = rs.getInt(4);
                appointments.add(appointment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  appointments;
    }

}
