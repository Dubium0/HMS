
import  java.sql.*;
public class DBConnection {

    final  static String url = "jdbc:mysql://localhost:3306/cs202_project";
    final static String user  = "root";
    final static  String password = "Yns090955.q";

    public  static  Connection getConnection(){
        Connection myConn = null;

        try{
            myConn = DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            System.out.println("\nERROR::DATABASE CONNECTION FAILED");
        }
        return  myConn;
    }


}
