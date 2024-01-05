import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorController {



    public static  boolean addBooking(Appointment appointment, int room_id, int nurse_id){
        RoomAvailability  room_availability =  EntityController.getRoomAvailabilityForDateAndRoom(appointment.date,room_id);
        NurseAvailability nurseAvailability = NurseController.getNurseAvailabilityForDateAndNurse(appointment.date,nurse_id);
        Booking booking = null;
        if(nurseAvailability.availability && room_availability.availability){
            Connection myConn = DBConnection.getConnection();
            String query = "INSERT INTO BOOKING ( roomID, nurseID) VALUES\n" +
                    "(? , ?);";

            try{
                PreparedStatement stmt = myConn.prepareStatement(query);
                stmt.setInt(1,room_id);
                stmt.setInt(2,nurse_id);

                int r = stmt.executeUpdate();
                if(r>0){
                    stmt.close();
                    if(EntityController.incrementRoomPatientCountForDate(appointment.date,room_id)){
                        System.out.println("Room " + room_id  + "  updated");
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }



        }

        return  true;
    }

    private  static  boolean updateAppointmentBooking(Appointment appointment, int booking_id){
        return true;

    }


}
