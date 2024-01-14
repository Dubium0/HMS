import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {

    public static ArrayList<ArrayList<String>> getPatientNumberbyDepartment(){
        Connection myConn = DBConnection.getConnection();
        String query = "Select count(appointmentDate) as patientCount, departmentName from (SELECT appointmentDate,appointment.patientId,appointment.doctorId,departmentId FROM appointment,booking,doctor,expertise where  doctor.expertiseId = expertise.expertiseId and doctor.doctorId = appointment.doctorId) as temp right join department on department.departmentid = temp.departmentId group by departmentName ;";
        ArrayList<ArrayList<String>> array_list = new ArrayList<>();
        try {
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rs=  stmt.executeQuery();

            while ( rs.next()){
                String dept_name = rs.getString("departmentName");
                String patientCount = String.valueOf(rs.getInt(1));
                ArrayList<String> temp = new ArrayList<>();
                temp.add(dept_name);
                temp.add(patientCount);
                array_list.add(temp);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return  array_list;
    }

    // DONE : CAN ADD NURSE
    // DONE : CAN ADD ADMIN
    // DONE : CAN ADD DOCTOR

    // göz kanatan kod satırları geliyor

}
