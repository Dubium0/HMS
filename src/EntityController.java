import java.sql.*;
import java.util.ArrayList;

public class EntityController {



    public  static int addExpertise( Expertise expertise) {
        // if success, returns expertise id that is generated
        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO EXPERTISE (departmentID, expertiseName) VALUES ( ? , ? );";
        int id =  -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,expertise.department_id);
            stmt.setString(2,expertise.name);
            int r = stmt.executeUpdate();
            if(r>0){
                ResultSet rs= stmt.getGeneratedKeys();
                if (rs.next())  id   = rs.getInt(1);
                expertise.expertise_id = id;
            }
            stmt.close();


        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    public  static ArrayList<Expertise> getExpertises(){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM EXPERTISE;";
        ArrayList<Expertise> expertises = new ArrayList<Expertise>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Expertise expertise  = new Expertise(rs.getInt(2),rs.getString(3));
                expertise.expertise_id = rs.getInt(1);
                expertises.add(expertise);
            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  expertises;
    }

    public  static ArrayList<Expertise> getExpertisesByDepartmentID(int department_id) {
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM EXPERTISE where EXPERTISE.departmentID =  ?;";
        ArrayList<Expertise> expertises = new ArrayList<Expertise>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,department_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Expertise expertise  = new Expertise(rs.getInt(2),rs.getString(3));
                expertise.expertise_id = rs.getInt(1);
                expertises.add(expertise);
            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  expertises;
    }
    public  static Expertise getExpertiseByName(String name) {
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM EXPERTISE where EXPERTISE.expertiseName =  ?;";
        Expertise expertise = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                expertise  = new Expertise(rs.getInt(2),rs.getString(3));
                expertise.expertise_id = rs.getInt(1);

            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  expertise;
    }

    public  static  Expertise getExpertiseByID(int expertise_id){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM EXPERTISE where EXPERTISE.expertiseID =  ?;";
        Expertise expertise = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,expertise_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                expertise  = new Expertise(rs.getInt(2),rs.getString(3));
                expertise.expertise_id = rs.getInt(1);

            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  expertise;

    }



    public  static int addDepartment( Department department){
        // if success, returns department id that is generated
        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO DEPARTMENT (departmentName) VALUES(?);";
        int id =  -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,department.name);
            int r = stmt.executeUpdate();
            if(r>0){
                ResultSet rs= stmt.getGeneratedKeys();
                if (rs.next())  id   = rs.getInt(1);
                department.department_id = id;
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;

    }

    public  static  ArrayList<Department> getDepartments(){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM DEPARTMENT;";
        ArrayList<Department> departments = new ArrayList<Department>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Department department  = new Department(rs.getString(2));
                department.department_id = rs.getInt(1);
                departments.add(department);
            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  departments;

    }

    public  static  Department getDepartmentByID(int departmenID){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM DEPARTMENT where DEPARTMENT.departmentID =  ?;";
        Department department = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,departmenID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                department  = new Department(rs.getString(2));
                department.department_id = rs.getInt(1);

            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  department;
    }

    public  static  Department getDepartmentByName(String name){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM DEPARTMENT where DEPARTMENT.departmentName =  ?;";
        Department department = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                department  = new Department(rs.getString(2));
                department.department_id = rs.getInt(1);

            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  department;
    }


    public  static int addRoom( Room room){
        // if success, returns department id that is generated
        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO ROOM (roomType, roomCapacity) VALUES (?, ? );";
        int id =  -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,room.roomType);
            stmt.setInt(1,room.room_capacity);
            int r = stmt.executeUpdate();
            if(r>0){
                ResultSet rs= stmt.getGeneratedKeys();
                if (rs.next())  id   = rs.getInt(1);
                room.room_id = id;
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;

    }

    public  static  ArrayList<Room> getRooms(){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM ROOM;";
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Room room  = new Room(rs.getString(2),rs.getInt(3));
                room.room_id = rs.getInt(1);
                rooms.add(room);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  rooms;
    }

    public  static  ArrayList<Room> getRoomsByType(String roomType){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM ROOM WHERE  ROOM.roomType  = ?;";
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,roomType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Room room  = new Room(rs.getString(2),rs.getInt(3));
                room.room_id = rs.getInt(1);
                rooms.add(room);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  rooms;

    }

    public  static  Room getRoomByID(int room_id){
        Connection myConn   = DBConnection.getConnection();
        String query = "Select  * FROM ROOM WHERE  ROOM.roomID  = ?;";
        Room room = null;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,room_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                room = new Room(rs.getString(2),rs.getInt(3));
                room.room_id = rs.getInt(1);

            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  room;
    }



    public  static RoomAvailability getRoomAvailabilityForDateAndRoom(Date date,int room_id){
        Connection myConn = DBConnection.getConnection();
        String query = "SElECT * FROM ROOM_AVAILABILITY where ROOM_AVAILABILITY.date_ = ? and ROOM_AVAILABILITY.roomID = ? ;";
        RoomAvailability roomAvailability =null;
        try{
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setDate(1,date);
            stmt.setInt(2,room_id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                roomAvailability = new RoomAvailability(room_id,date);
                roomAvailability.patient_count = rs.getInt(3);
                roomAvailability.availability = rs.getBoolean(4);
            }
            stmt.close();
        }catch (SQLException e){
            // if not exist  than it is empty
            roomAvailability = new RoomAvailability(room_id,date );
            roomAvailability.patient_count = 0;
            roomAvailability.availability = true;

            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  roomAvailability;
    }

    public  static ArrayList<RoomAvailability> getStatedRoomAvailabilityForRoom(int room_id){
        Connection myConn = DBConnection.getConnection();
        String query = "SElECT * FROM ROOM_AVAILABILITY where ROOM_AVAILABILITY.roomID = ? ;";
        ArrayList<RoomAvailability> roomAvailabilities  = new ArrayList<RoomAvailability>();

        try{
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,room_id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                RoomAvailability roomAvailability = new RoomAvailability(room_id,rs.getDate(2));
                roomAvailability.patient_count  = rs.getInt(3);
                roomAvailability.availability = rs.getBoolean(4);
                roomAvailabilities.add(roomAvailability);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  roomAvailabilities;


    }

    public  static ArrayList<RoomAvailability> getStatedRoomAvailabilityForDate(Date date){
        Connection myConn = DBConnection.getConnection();
        String query = "SElECT * FROM ROOM_AVAILABILITY where ROOM_AVAILABILITY.date_ = ? ;";
        ArrayList<RoomAvailability> roomAvailabilities  = new ArrayList<RoomAvailability>();

        try{
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setDate(1,date);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                RoomAvailability roomAvailability = new RoomAvailability(rs.getInt(1),rs.getDate(2));
                roomAvailability.patient_count  = rs.getInt(3);
                roomAvailability.availability = rs.getBoolean(4);
                roomAvailabilities.add(roomAvailability);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            myConn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  roomAvailabilities;


    }





}
