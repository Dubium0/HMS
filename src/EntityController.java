import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            stmt.setInt(1,room.room_capacity);
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





}
