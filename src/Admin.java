public class Admin extends  User {


    public  Admin(

            String name_surname,
            int age,
            String user_name,
            String gender,
            String password){

        this.name_surname = name_surname;
        this.age =age;
        this.user_name = user_name;
        this.gender = gender;
        this.password = password;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
