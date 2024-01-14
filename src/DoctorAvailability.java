import java.sql.Date;
import java.sql.Timestamp;

public class DoctorAvailability {
    public  int doctor_id;
    public Timestamp date;
    public  boolean availability;

    public  DoctorAvailability(int doctor_id, Timestamp date, boolean availability){
        this.doctor_id = doctor_id;
        this.date = date;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\nInfo Doctor Availability for doctor " + doctor_id + " at " + date + " is available : " + availability  +"\n";
    }
}
