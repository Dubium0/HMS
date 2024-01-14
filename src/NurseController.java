import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NurseController {



    public  static  boolean getNurseAvailabilityForDateAndNurse(Timestamp date , int nurse_id){

        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? and  NURSE_AVAILABILITY.date_ = ? ;";

        boolean isAvailable = false;
        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);
            stmt.setTimestamp(2,date);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                isAvailable  =  true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  isAvailable;

    }
    public  static  NurseAvailability getNurseAvailabilityForDateAndNurse_NurseAvailability(Timestamp date , int nurse_id){

        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? and  NURSE_AVAILABILITY.date_ = ? ;";
        NurseAvailability availability =  new NurseAvailability(nurse_id,date,true);

        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);
            stmt.setTimestamp(2,date);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                availability = new NurseAvailability(nurse_id,date,rs.getBoolean("availability"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  availability;

    }

    public  static ArrayList<NurseAvailability> getStatedNurseAvailabilitesByDate( Timestamp date){
        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.date_ = ? ;";
        ArrayList<NurseAvailability> nurseAvailabilities  = new ArrayList<NurseAvailability>();

        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setTimestamp(1,date);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                NurseAvailability nurseAvailability = new NurseAvailability(rs.getInt(1),date,rs.getBoolean(3));
                nurseAvailabilities.add(nurseAvailability);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurseAvailabilities;

    }
    public  static ArrayList<NurseAvailability> getStatedNurseAvailabilitesByNurseID( int nurse_id){
        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? ;";
        ArrayList<NurseAvailability> nurseAvailabilities  = new ArrayList<NurseAvailability>();


        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                NurseAvailability nurseAvailability = new NurseAvailability(rs.getInt(1),rs.getTimestamp(2),rs.getBoolean(3));
                nurseAvailabilities.add(nurseAvailability);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurseAvailabilities;

    }
    public  static ArrayList<NurseAvailability> getNurseAvailabilitiesForNext_7_days(){
        // sabah 8 akşam 17
        ArrayList<NurseAvailability> availabilities = new ArrayList<>();
        ArrayList<Nurse> nurses= UserController.getNurses();
        LocalDate localDate = LocalDate.now();
        for (int k  = 0; k <7 ; k++){

            for(int i = 8 ; i <=17 ;i++){
                String currentDate  = localDate.toString();

                if( i< 10){
                    currentDate +=  " 0" + i + ":00:00";
                }else{
                    currentDate +=  " " + i + ":00:00";
                }

                Timestamp date_ =Timestamp.valueOf(currentDate);

                for(Nurse n :nurses){
                    NurseAvailability availability =getNurseAvailabilityForDateAndNurse_NurseAvailability(date_,n.user_id);

                    if(availability!= null){
                        availabilities.add(availability);
                    }else{
                        availabilities.add(new NurseAvailability(n.user_id,date_,true));

                    }
                }

            }
            localDate.plusDays(1);
        }

        return  availabilities;

    }
    public  static ArrayList<NurseAvailability> getNurseAvailabilitiesForNext_7_days(int nurseID){
        // sabah 8 akşam 17
        ArrayList<NurseAvailability> availabilities = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        for (int k  = 0; k <7 ; k++){

            for(int i = 8 ; i <=17 ;i++){
                String currentDate  = localDate.toString();

                if( i< 10){
                    currentDate +=  " 0" + i + ":00:00";
                }else{
                    currentDate +=  " " + i + ":00:00";
                }

                Timestamp date_ =Timestamp.valueOf(currentDate);


                NurseAvailability availability =getNurseAvailabilityForDateAndNurse_NurseAvailability(date_,nurseID);

                if(availability!= null){
                    availabilities.add(availability);
                }else{
                    availabilities.add(new NurseAvailability(nurseID,date_,true));

                }


            }
            localDate.plusDays(1);
        }

        return  availabilities;

    }

    public  static ArrayList<NurseAvailability> getNurseAvailabilitiesForDateLoseless(Timestamp date){
        // sabah 8 akşam 17
        ArrayList<NurseAvailability> availabilities = new ArrayList<>();
        ArrayList<Nurse> nurses= UserController.getNurses();

        for(Nurse n :nurses){
            NurseAvailability availability =getNurseAvailabilityForDateAndNurse_NurseAvailability(date,n.user_id);

            if(availability!= null){
                availabilities.add(availability);
            }else{
                availabilities.add(new NurseAvailability(n.user_id,date,true));

            }
        }



        return  availabilities;

    }

    public  static boolean updateNurseAvailabilityForDateAndNurse(Timestamp date , int nurse_id, boolean isAvailable){

        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO NURSE_AVAILABILITY (nurseID, date_, availability) VALUES\n" +
                "( ?, ?, ?) ON DUPLICATE KEY UPDATE availability = ?;";

        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,nurse_id);
            stmt.setTimestamp(2,date);
            stmt.setBoolean(3,isAvailable);
            stmt.setBoolean(4,isAvailable);

            int r = stmt.executeUpdate();
            if(r>0) return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }

    public  static  ArrayList<Appointment> getUpcomingAppointmentsByNurseID(int nurse_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment,booking  where appointment.bookingId = booking.bookingId and booking.nurseID = ? AND appointment.appointmentDate > CURRENT_DATE()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,nurse_id);
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

    public  static  ArrayList<Appointment> getPastAppointmentsByNurseID(int nurse_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment,booking  where appointment.bookingId = booking.bookingId and booking.nurseID = ? AND appointment.appointmentDate < CURRENT_DATE()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,nurse_id);
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
