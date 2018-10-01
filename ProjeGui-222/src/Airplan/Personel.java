package Airplan;

import java.util.HashMap;
import java.util.Map;

public class Personel extends HumanAbstract {

    private String ID;
    private String Experience;
    private String GraduationLevel;
    private String WorkingStyle;
    private String Salary;
    private String Permission ;

    public Personel(String _ID, String _Name, String _Surname, String _Age, String _Gender, String _TelephoneNumber,
                    String _Nation, String _BloodGroup, String _Experience, String _GraduationLevel,
                    String _WorkingStyle, String _Salary,String _Permission){
        super(_Name,_Surname,_Age,_Gender,_TelephoneNumber,_Nation,_BloodGroup);
        ID = _ID;
        Experience = _Experience;
        GraduationLevel = _GraduationLevel;
        WorkingStyle = _WorkingStyle;
        Salary = _Salary;
        Permission= _Permission ;
    }

    public String getExperience() {
        return Experience;
    }

    public String getGraduationLevel() {
        return GraduationLevel;
    }

    public String getWorkingStyle() {
        return WorkingStyle;
    }

    public String getSalary() {
        return Salary;
    }

    public String getID() {
        return ID;
    }

    public String getPermission() {
        return Permission;
    }

    public String toString(){
        String ret = super.toString();
        ret += ID +" "+Experience+" "+GraduationLevel+" "+WorkingStyle+" "+Salary+" "+Permission;
        return ret;
    }


    public Map<String,String> getInfo(){
        Map<String,String > map = new HashMap<>();
        map.put("id",ID);
        map.put("name",getName());
        map.put("surname",getSurname());
        map.put("age",getAge());
        map.put("gender",getGender());
        map.put("tel",getTelephoneNumber());
        map.put("nation",getNation());
        map.put("blood",getBloodGroup());

        map.put("salary",getSalary());
        map.put("job",getWorkingStyle());

        return map;
    }

    @Override
    public int compareTo(HumanAbstract other) {

        return Integer.valueOf(this.getID()) - Integer.valueOf(((Personel) other).getID());
    }
}
