public class Expertise {

    public int department_id;
    public int expertise_id;
    public  String name;

    public  Expertise(int department_id,String name){
        this.department_id  = department_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  "\nInfo Expertise " + expertise_id + "\n"
                +"Department ID : " + department_id + "\n"
                +"Expertise Name : " + name + "\n";
    }
}
