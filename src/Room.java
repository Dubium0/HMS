public class Room {

    public  int room_id;
    public  String roomType;

    public  int room_capacity;

    public  Room(String roomType , int room_capacity){
        this.roomType = roomType;
        this.room_capacity = room_capacity;
    }

    @Override
    public String toString() {
        return "\nId :  " + room_id + "\n"
                +"Room type : " + roomType + "\n"
                +"Room Capacity : " + room_capacity + "\n";
    }
}
