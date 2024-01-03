public class Booking {
    public  int booking_id;
    public  int room_id;
    public  int nurse_id;

    public  Booking(int room_id, int nurse_id){
        this.room_id = room_id;
        this.nurse_id = nurse_id;

    }

    @Override
    public String toString() {
        return "\nInfo Booking " + booking_id + "\n"
                +"Room ID=" + room_id + "\n"
                +"Nurse ID=" + nurse_id + "\n";
    }
}
