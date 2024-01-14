import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
            String date_off  = "2024-01-14 10:00:00";
            DoctorController.updateDoctorAvailability(Timestamp.valueOf(date_off),2,false);
            for(DoctorAvailability a : DoctorController.getDoctorAvailabilitiesFiltered(null,0,0,9,18)){
                System.out.println(a);
            }
    }
}
