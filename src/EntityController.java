import java.sql.*;

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


        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
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
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;

    }

    public  static int addRoom( Room room){
        // if success, returns department id that is generated
        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO ROOM (roomType, roomCapacity) VALUES (?, ? );";
        int id =  -1;
        try {
            PreparedStatement stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,room.roomType);
            stmt.setInt(2,room.room_capacity);
            int r = stmt.executeUpdate();
            if(r>0){
                ResultSet rs= stmt.getGeneratedKeys();
                if (rs.next())  id   = rs.getInt(1);
                room.room_id = id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;

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
                roomAvailability = new RoomAvailability(room_id,date,rs.getInt(3),rs.getBoolean(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  roomAvailability;
    }

    public  static boolean incrementRoomPatientCountForDate(Date date, int room_id){
        RoomAvailability currentRoomAvailability  = getRoomAvailabilityForDateAndRoom(date,room_id);
        if(currentRoomAvailability ==null)return  false;


        if(!currentRoomAvailability.availability){
            System.out.println( currentRoomAvailability);
            return false;
        }

        boolean flag = false;
        boolean returnValue = false;

        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO ROOM_AVAILABILITY (roomID, date_, patientCount,availability) VALUES\n" +
                "( ? , ? ,1,  true) ON DUPLICATE KEY UPDATE patientCount = patientCount +1; ";

        try {
            PreparedStatement  stmt = myConn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,room_id);
            stmt.setDate(2,date);

            int r = stmt.executeUpdate();
            if(r>0){
                flag = true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        if(flag) {


            String query_2 = "UPDATE ROOM_AVAILABILITY\n" +
                    "\tset ROOM_AVAILABILITY.availability = false\n" +
                    "    where ROOM_AVAILABILITY.roomID = ?  and ROOM_AVAILABILITY.date_ = ? \n" +
                    "    and ROOM_AVAILABILITY.patientCount = (\n" +
                    "    select roomCapacity\n" +
                    "    from ROOM\n" +
                    "    where ROOM.roomID =  ROOM_AVAILABILITY.roomID\n" +
                    "    ); ";
            try {
                PreparedStatement stmt = myConn.prepareStatement(query_2,PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, room_id);
                stmt.setDate(2,date);
                int r  = stmt.executeUpdate();

                if(r>0){
                    returnValue  =true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  returnValue;
    }


}
