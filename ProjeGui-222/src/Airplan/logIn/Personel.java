package Airplan.logIn;

/**
 * Created by serhan on 24.04.2018.
 */
public class Personel implements Comparable<Personel> {
    private int id;
    private String password;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String telephoneNumber;
    private String nation;
    private String bloodGroup;
    private String graduationLevel;
    private int salary;
    private String role;

    public Personel(int id, String name, String surname, int age, String gender, String telephoneNumber, String nation, String bloodGroup, String graduationLevel, int salary,String role,String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.nation = nation;
        this.bloodGroup = bloodGroup;
        this.graduationLevel = graduationLevel;
        this.salary = salary;
        this.role = role;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGraduationLevel() {
        return graduationLevel;
    }

    public void setGraduationLevel(String graduationLevel) {
        this.graduationLevel = graduationLevel;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Personel p) {
        return Integer.compare(id,p.id);
    }
}
