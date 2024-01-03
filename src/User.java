
public  class User {

    public  String name_surname;
    public  int age;
    public  String user_name;
    public  int password;
    public  int user_id;
    public  String gender;

    @Override
    public String toString() {
        return "\nInfo User " + user_id + "\n"
                +"Name Surname : " + name_surname  +"\n"
                +"Age : " + age + "\n"
                +"User name : " + user_name + "\n"
                +"Gender : " + gender + "\n"
                +"Password : " + password + "\n";
    }
}
