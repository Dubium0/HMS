import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class DoctorController {



    public static  Booking addBooking(Appointment appointment, int room_id, int nurse_id){
        RoomAvailability  room_availability =  EntityController.getRoomAvailabilityForDateAndRoom(appointment.date,room_id);
        boolean nurseAvailability = NurseController.getNurseAvailabilityForDateAndNurse(appointment.date,nurse_id);
        Booking booking = null;
        if(nurseAvailability && room_availability.availability){
            Connection myConn = DBConnection.getConnection();
            String query = "INSERT INTO BOOKING ( roomID, nurseID) VALUES\n" +
                    "(? , ?);";

            try{
                PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,room_id);
                stmt.setInt(2,nurse_id);

                int r = stmt.executeUpdate();

                if(r>0){
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()){
                        booking = new Booking(room_id,nurse_id);
                        booking.booking_id  = rs.getInt(1);
                        stmt.close();
                        if(incrementRoomPatientCountForDate(appointment.date,room_id)){
                            System.out.println("Room " + room_id  + "  updated");
                        }
                        if(updateAppointmentBooking(appointment,booking.booking_id)){
                            System.out.println("Appointment " + appointment.date  + "  updated");
                        }
                    }


                }
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }


            try {
                myConn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return  booking;
    }
    public static  Booking getBookingByBookingId(int booking_id){
        Connection myConn = DBConnection.getConnection();
        Booking booking =null;
        String query  =  "SELECT * FROM cs202_project.booking where bookingId = ?";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,booking_id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                booking = new Booking(rs.getInt(2),rs.getInt(3));
                booking.booking_id = booking_id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  booking;
    }
    private   static boolean incrementRoomPatientCountForDate(Timestamp date, int room_id){
        RoomAvailability currentRoomAvailability  = EntityController.getRoomAvailabilityForDateAndRoom(date,room_id);
        if(currentRoomAvailability ==null)return  false;


        if(!currentRoomAvailability.availability){
            System.out.println( currentRoomAvailability);
            return false;
        }

        boolean flag = false;
        boolean returnValue = false;

        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO ROOM_AVAILABILITY (roomID, date_, patientCount,availability) VALUES\n" +
                "( ? , ? ,1,  true) ON DUPLICATE KEY UPDATE patientCount = patientCount +1; ";

        try {
            PreparedStatement  stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,room_id);
            stmt.setTimestamp(2,date);

            int r = stmt.executeUpdate();
            if(r>0){
                flag = true;
            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }


        if(flag) {


            String query_2 = "UPDATE ROOM_AVAILABILITY\n" +
                    "\tset ROOM_AVAILABILITY.availability = false\n" +
                    "    where ROOM_AVAILABILITY.roomID = ?  and ROOM_AVAILABILITY.date_ = ? \n" +
                    "    and ROOM_AVAILABILITY.patientCount = (\n" +
                    "    select roomCapacity\n" +
                    "    from ROOM\n" +
                    "    where ROOM.roomID =  ROOM_AVAILABILITY.roomID\n" +
                    "    ); ";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2,PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, room_id);
                stmt.setTimestamp(2,date);
                int r  = stmt.executeUpdate();

                if(r>0){
                    returnValue  =true;
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  returnValue;
    }
    private  static  boolean updateAppointmentBooking(Appointment appointment, int booking_id){

        Connection myConn = DBConnection.getConnection();
        String query= "UPDATE APPOINTMENT\n" +
                "     SET APPOINTMENT.bookingID = ?\n" +
                "     where APPOINTMENT.doctorID = ?  and APPOINTMENT.patientID =   ? and  APPOINTMENT.appointmentDate = ?;";

        try{
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,booking_id);
            stmt.setInt(2,appointment.doctor_id);
            stmt.setInt(3,appointment.patient_id);
            stmt.setTimestamp(4,appointment.date);
            int r = stmt.executeUpdate();
            if(r>0){
                return  true;
            }

            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
    private  static  boolean decrementRoomPatientCount(Timestamp date, int room_id){
       String query =  "UPDATE room_availability\n" +
                "set room_availability.patientCount = room_availability.patientCount-1, room_availability.availability   = true\n" +
                "where room_availability.date_  = ? and ROOM_AVAILABILITY.roomID  =?;";

       Connection myConn  = DBConnection.getConnection();
       try{
           PreparedStatement stmt = myConn.prepareStatement(query);
           stmt.setTimestamp(1,date);
           stmt.setInt(2,room_id);
           int r = stmt.executeUpdate();
           if(r>0){
               return  true;
           }

       }catch (SQLException e){
           e.printStackTrace();
       }
       return  false;
    }
    public  static boolean deleteBooking(int bookingID, Timestamp date){
        String query=  "delete from BOOKING\n" +
                       "where BOOKING.bookingID =   ? ;";

        Connection myConn = DBConnection.getConnection();
        boolean result = false;
        try{
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,bookingID);
            int r  = stmt.executeUpdate();
            if(r>0){
                ResultSet rs = stmt.getResultSet();
                if(rs.next()){

                    NurseController.updateNurseAvailabilityForDateAndNurse(date,rs.getInt("nurseID"),false);
                    decrementRoomPatientCount(date, rs.getInt("roomID"));

                }
                result =   true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            result =  false;
        }
        return result;
    }

    public  static  boolean updateDoctorAvailability(Date date, int doctor_id, boolean isAvailable){
        Connection myConn = DBConnection.getConnection();
        String query =  "INSERT INTO DOCTOR_AVAILABILITY (doctorID, date_,availability) VALUES\n" +
                "(? ,  ?  ,  ? ) ON DUPLICATE KEY UPDATE availability =  ? ;";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,doctor_id);
            stmt.setDate(2,date);
            stmt.setBoolean(3,isAvailable);
            stmt.setBoolean(4,isAvailable);
            int r = stmt.executeUpdate();
            if(r>0){
                stmt.close();
                return  true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;

    }



    public  static  boolean checkDoctorAvailability(Date date, int doctor_id){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT availability from DOCTOR_AVAILABILITY  where DOCTOR_AVAILABILITY.date_  = ? and DOCTOR_AVAILABILITY.doctorID = ? ; ";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setDate(1,date);
            stmt.setInt(2,doctor_id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBoolean(3);
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        // if does not exist it will return false
        return  false;


    }

    public static ArrayList<DoctorAvailability> getAllStatedAvailabilitiesByDoctorID(int doctor_id){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * from DOCTOR_AVAILABILITY  where  DOCTOR_AVAILABILITY.doctorID = ? ; ";
        ArrayList<DoctorAvailability> doctorAvailabilities = new ArrayList<DoctorAvailability>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,doctor_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                DoctorAvailability availability = new DoctorAvailability(doctor_id,rs.getTimestamp(2),rs.getBoolean(3));
                doctorAvailabilities.add(availability);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  doctorAvailabilities;

    }
    public  static ArrayList<DoctorAvailability> getDoctorAvailabilitiesForNext_7_days(){
        // sabah 8 akşam 17
        ArrayList<DoctorAvailability> availabilities = new ArrayList<>();
        ArrayList<Doctor> doctors= UserController.getDoctors();
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

                for(Doctor d :doctors){
                    DoctorAvailability availability =getAllStatedAvailabilitiesByDateAndID(date_,d.user_id);

                    if(availability !=null){
                        availabilities.add(availability);
                    }else{
                        availabilities.add(new DoctorAvailability(d.user_id,Timestamp.valueOf(currentDate),true));

                    }
                }

            }

        }

        return  availabilities;

    }

    public  static  ArrayList<DoctorAvailability> getAllDoctorsAvailabilities(){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * from DOCTOR_AVAILABILITY  ; ";
        ArrayList<DoctorAvailability> doctorAvailabilities = new ArrayList<DoctorAvailability>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                DoctorAvailability availability = new DoctorAvailability(rs.getInt(1),rs.getTimestamp(2),rs.getBoolean(3));
                doctorAvailabilities.add(availability);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  doctorAvailabilities;
    }

    public static ArrayList<DoctorAvailability> getAllStatedAvailabilitiesByDate(Date date){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * from DOCTOR_AVAILABILITY  where  DOCTOR_AVAILABILITY.date_ = ? ; ";
        ArrayList<DoctorAvailability> doctorAvailabilities = new ArrayList<DoctorAvailability>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setDate(1,date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                DoctorAvailability availability = new DoctorAvailability(rs.getInt(1),rs.getTimestamp(2),rs.getBoolean(3));
                doctorAvailabilities.add(availability);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  doctorAvailabilities;

    }
    public static DoctorAvailability getAllStatedAvailabilitiesByDateAndID(Timestamp date,int id){
        Connection myConn = DBConnection.getConnection();
        String query  = "SELECT * from DOCTOR_AVAILABILITY  where  DOCTOR_AVAILABILITY.date_ = ?  and DOCTOR_AVAILABILITY.doctorID  = ?; ";
        DoctorAvailability availability = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setTimestamp(1,date);
            stmt.setInt(2,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
               availability = new DoctorAvailability(rs.getInt(1),rs.getTimestamp(2),rs.getBoolean(3));

            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  availability;

    }

    public  static  ArrayList<Appointment> getAppointmentsByDoctorID(int doctor_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.doctorID = ?";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,doctor_id);
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


    public  static  ArrayList<Appointment> getUpcomingAppointmentsByDoctorID(int doctor_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.doctorID = ? AND appointment.appointmentDate > CURRENT_DATE()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,doctor_id);
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

    public  static  ArrayList<Appointment> getPastAppointmentsByDoctorID(int doctor_id){

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Connection myConn = DBConnection.getConnection();

        String query  =  "Select * from appointment  where appointment.doctorID = ? AND appointment.appointmentDate < CURRENT_DATE()";
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,doctor_id);
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
