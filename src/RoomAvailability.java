import java.sql.Date;
import java.sql.Timestamp;

public class RoomAvailability {

    public  int room_id;
    public Timestamp date;
    public int patient_count;

    public  boolean availability;

    public  RoomAvailability(int room_id, Timestamp date){
        this.room_id = room_id;
        this.date = date;

    }

    @Override
    public String toString() {
        return "\nInfo Room Availability for room " + room_id + " at " + date +"\n";
    }
}
