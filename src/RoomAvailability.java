import java.sql.Date;

public class RoomAvailability {

    public  int room_id;
    public Date date;
    public int patient_count;

    public  boolean availability;

    public  RoomAvailability(int room_id, Date date){
        this.room_id = room_id;
        this.date = date;

    }

    @Override
    public String toString() {
        return "\nInfo Room Availability for room " + room_id + " at " + date +"\n";
    }
}
