package Airplan;

public abstract class HumanAbstract implements Comparable<HumanAbstract> {

    private String Name;
    private String Surname;
    private String Age;
    private String Gender;
    private String TelephoneNumber;
    private String Nation;
    private String BloodGroup;

    public HumanAbstract(String _Name, String _Surname, String _Age,
                         String _Gender, String _TelephoneNumber, String _Nation, String _BloodGroup){
        Name = _Name;
        Surname = _Surname;
        Age = _Age;
        Gender = _Gender;
        TelephoneNumber = _TelephoneNumber;
        Nation = _Nation;
        BloodGroup = _BloodGroup;
    }

    public HumanAbstract(){
        Name = null;
        Surname = null;
        Age = null;
        Gender = null;
        TelephoneNumber = null;
        Nation = null;
        BloodGroup = null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("The Record :\n");
        sb.append(Name);
        sb.append(" ");
        sb.append(Surname);
        sb.append(" ");
        sb.append(Age);
        sb.append(" ");
        sb.append(Gender);
        sb.append(" ");
        sb.append(TelephoneNumber);
        sb.append(" ");
        sb.append(Nation);
        sb.append(" ");
        sb.append(BloodGroup);
        return sb.toString();
    }

    @Override
    public int compareTo(HumanAbstract other){
        if(this.Name == other.Name && this.Surname == other.Surname
                && this.Age == other.Age && this.BloodGroup == other.BloodGroup){
            return 1;
        }
        return 0;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getAge() {
        return Age;
    }

    public String getGender() {
        return Gender;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public String getNation() {
        return Nation;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }
}
