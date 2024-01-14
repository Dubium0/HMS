import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Timestamp ts = Timestamp.valueOf("2024-02-3 18:00:00");
       for(RoomAvailability r :  EntityController.getRoomAvailabilitiesForDateLoseless(ts)){
           System.out.println(r);
       }
    }
}
