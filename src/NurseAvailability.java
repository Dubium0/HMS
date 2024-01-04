import java.sql.Date;

public class NurseAvailability {

    public  int nurse_id;
    public Date date;
    public  boolean availability;

    public  NurseAvailability(int nurse_id, Date date, boolean availability){
        this.nurse_id = nurse_id;
        this.date = date;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\nInfo Nurse Availability for nurse " + nurse_id + " at " + date +"\n";
    }
}
