import java.sql.Date;
import java.sql.Timestamp;

public class NurseAvailability {

    public  int nurse_id;
    public Timestamp date;
    public  boolean availability;

    public  NurseAvailability(int nurse_id, Timestamp date, boolean availability){
        this.nurse_id = nurse_id;
        this.date = date;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\nInfo Nurse Availability for nurse " + nurse_id + " at " + date +"\n";
    }
}
