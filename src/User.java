
public  class User {

    public  String name_surname;
    public  int age;
    public  String user_name;
    public  String password;
    public  int user_id;
    public  String gender;

    @Override
    public String toString() {
        return "\nId : " + user_id + "\n"
                +"Name Surname : " + name_surname  +"\n"
                +"Age : " + age + "\n"
                +"Gender : " + gender + "\n";
    }
}
