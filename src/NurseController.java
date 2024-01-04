import java.sql.*;

public class NurseController {



    public  static  NurseAvailability getNurseAvailabilityForDateAndNurse(Date date , int nurse_id){

        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? ,  NURSE_AVAILABILITY.date_ = ? ;";

        NurseAvailability nurseAvailability= null;
        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);
            stmt.setDate(2,date);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                nurseAvailability = new NurseAvailability(nurse_id,date,rs.getBoolean(3));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurseAvailability;

    }
}
