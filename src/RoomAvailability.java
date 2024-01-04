import java.sql.Date;

public class RoomAvailability {

    public  int room_id;
    public Date date;
    public int patient_count;

    public  boolean availability;

    public  RoomAvailability(int room_id, Date date, int patient_count, boolean availability){
        this.room_id = room_id;
        this.date = date;
        this.patient_count = patient_count;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\nInfo Room Availability for room " + room_id + " at " + date +"\n";
    }
}
