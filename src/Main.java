import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Doctor> doctors  =  UserController.getFilteredDoctors("Cardiologist",1 ,4,9,12);
        for(int i = 0 ; i< doctors.size() ;i++){
            System.out.println(doctors.get(i).toString());
        }

    }
}
