import  java.sql.Date;
public class Appointment {

    public Date date;

    public int patient_id;
    public int doctor_id;

    public int booking_id = -1;

    public  Appointment(Date  date, int patient_id,  int doctor_id){
        this.date =date;
        this.patient_id = patient_id ;
        this.doctor_id = doctor_id;
    }

    @Override
    public String toString() {
        return "\nInfo Appointment date " + date + "\n"
                +"Patient ID : " + patient_id + "\n"
                +"Doctor ID : " + doctor_id + "\n"
                +"Booking ID : " + booking_id + "\n";
    }
}
