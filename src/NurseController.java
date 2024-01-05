import java.sql.*;
import java.util.ArrayList;

public class NurseController {



    public  static  boolean getNurseAvailabilityForDateAndNurse(Date date , int nurse_id){

        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? and  NURSE_AVAILABILITY.date_ = ? ;";

        boolean isAvailable = false;
        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);
            stmt.setDate(2,date);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                isAvailable  =  true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  isAvailable;

    }

    public  static ArrayList<NurseAvailability> getStatedNurseAvailabilitesByDate( Date date){
        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.date_ = ? ;";
        ArrayList<NurseAvailability> nurseAvailabilities  = new ArrayList<NurseAvailability>();

        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setDate(1,date);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                NurseAvailability nurseAvailability = new NurseAvailability(rs.getInt(1),date,rs.getBoolean(3));
                nurseAvailabilities.add(nurseAvailability);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurseAvailabilities;

    }
    public  static ArrayList<NurseAvailability> getStatedNurseAvailabilitesByNurseID( int nurse_id){
        Connection myConn = DBConnection.getConnection();

        String query  = "SELECT *  from NURSE_AVAILABILITY where  NURSE_AVAILABILITY.nurseID = ? ;";
        ArrayList<NurseAvailability> nurseAvailabilities  = new ArrayList<NurseAvailability>();


        try {
            PreparedStatement stmt= myConn.prepareStatement(query);

            stmt.setInt(1,nurse_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                NurseAvailability nurseAvailability = new NurseAvailability(rs.getInt(1),rs.getDate(2),rs.getBoolean(3));
                nurseAvailabilities.add(nurseAvailability);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  nurseAvailabilities;

    }

    public  static boolean updateNurseAvailabilityForDateAndNurse(Date date , int nurse_id, boolean isAvailable){

        Connection myConn = DBConnection.getConnection();
        String query = "INSERT INTO NURSE_AVAILABILITY (nurseID, date_, availability) VALUES\n" +
                "( ?, ?, ?) ON DUPLICATE KEY UPDATE availability = ?;";

        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1,nurse_id);
            stmt.setDate(2,date);
            stmt.setBoolean(3,isAvailable);
            stmt.setBoolean(4,isAvailable);

            int r = stmt.executeUpdate();
            if(r>0) return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
}
