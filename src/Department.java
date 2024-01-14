public class Department {

    public  int department_id;
    public  String name;

    public Department(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nInfo Department " + department_id + "\n"
                +"Department name : " + name + "\n";
    }
}
