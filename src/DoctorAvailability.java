import java.sql.Date;

public class DoctorAvailability {
    public  int doctor_id;
    public Date date;
    public  boolean availability;

    public  DoctorAvailability(int doctor_id, Date date, boolean availability){
        this.doctor_id = doctor_id;
        this.date = date;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\nInfo Doctor Availability for doctor " + doctor_id + " at " + date +"\n";
    }
}
