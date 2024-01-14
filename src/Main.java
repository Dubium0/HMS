import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Appointment> apps = PatientController.getAppointments(14);
        for(Appointment a : apps){
            PatientController.deleteAppointment(a);
        }

    }
}
