public class Doctor extends  User{

    public  int expertise_id;
    public  Doctor(

            String name_surname,
            int age,
            String user_name,
            String gender,
            int expertise_id,
            String password){

        this.name_surname = name_surname;
        this.age =age;
        this.user_name = user_name;
        this.gender = gender;
        this.password = password;
        this.expertise_id = expertise_id;

    }

    @Override
    public String toString() {
        return super.toString() + "Expertise : " + this.expertise_id + "\n";
    }
}
