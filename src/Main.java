public class Main {
    public static void main(String[] args) {
        System.out.println(PatientController.getPatient("Mia Thompson"));
        Patient a_patient = new Patient("Yunus Emre ASLAN", 21, "YunYun","Male", 1231231);
        if(PatientController.addPatient(a_patient)){
            System.out.println("YunYun added");
        }
    }
}
