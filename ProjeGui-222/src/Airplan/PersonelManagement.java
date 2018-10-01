package Airplan;

import java.io.*;
import java.util.*;

public class PersonelManagement extends CVSData  {

    public Map<String,Personel> personelMap;


    public PersonelManagement() throws Exception {
        personelMap = new HashMap<String, Personel>();
        add();
    }


    private void add() throws Exception {
        read("PersonelFile.csv");

        for(int i=1 ;i<getData().size()+1; ++i){
            Personel p1 = new Personel(getRecordLine(i)[0],getRecordLine(i)[1],getRecordLine(i)[2],
                    getRecordLine(i)[3],getRecordLine(i)[4],getRecordLine(i)[5],getRecordLine(i)[6],
                    getRecordLine(i)[7],getRecordLine(i)[8],getRecordLine(i)[9],getRecordLine(i)[10],
                    getRecordLine(i)[11],getRecordLine(i)[12]);
            personelMap.put(getRecordLine(i)[0],p1);
        }
    }

    public boolean add(Personel other) throws IOException{
        String[] newPersonel = new String[13];

        for(int index=1; index<getData().size();++index){
            if(getRecordLine(index)[0].equals(other.getID())) {
                return false;
            }
        }

        newPersonel[0] = other.getID();
        newPersonel[1] = other.getName();
        newPersonel[2] = other.getSurname();
        newPersonel[3] = other.getAge();
        newPersonel[4] = other.getGender();
        newPersonel[5] = other.getTelephoneNumber();
        newPersonel[6] = other.getNation();
        newPersonel[7] = other.getBloodGroup();
        newPersonel[8] = other.getExperience();
        newPersonel[9] = other.getGraduationLevel();
        newPersonel[10] = other.getWorkingStyle();
        newPersonel[11] = other.getSalary();
        newPersonel[12] = other.getPermission();
        addRecordLine(newPersonel);
        write("PersonelFile.csv");
        return true;
    }



    /**
     * Sorting the Data for giving type.
     * @param _type Name, Surname etc.
     * @throws IOException for write operation
     */
    public void sortAndWrite(String _type)throws IOException{
        switch (_type){
            case "ID":
                sort(0);
                break;
            case "Name":
                sort(1);
                break;
            case "Surname":
                sort(2);
                break;
            case "Age":
                sort(3);
                break;
            case "Gender":
                sort(4);
                break;
            case "TelephoneNumber":
                sort(5);
                break;
            case "Nation":
                sort(6);
                break;
            case "BloodGroup":
                sort(7);
                break;
            case "Experience":
                sort(8);
                break;
            case "GraduationLevel":
                sort(9);
                break;
            case "WorkingStyle":
                sort(10);
                break;
            case "Salary":
                sort(11);
                break;
            case "Permission":
                sort(12);
                break;
        }
    }

    /**
     * helper method for sortAndWrite
     * @param type Name = 0, Surname = 1, ... etc.
     * @throws IOException for write Method.
     */
    private void sort(int type) throws IOException{

        String [] temp = new String[getData().size()-1];
        MergeSort sort = new MergeSort();
        for(int i=1; i<=getData().size()-1; ++i){
            temp[i-1] = getData().get(i)[type];
        }
        sort.sort(temp);

        String[] buf = {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"};

        String[] header = {"ID","Name","Surname","Age","Gender","TelephoneNumber",
                "Nation","BloodGroup","Experience","GraduationLevel","WorkingStyle",
                "Salary","Permission"};

        ArrayList<String[]> temp2 = new ArrayList<>();
        int firstSize = getData().size();

        temp2.add(header);

        for(int k=0; k<temp.length; k++){
            for(int i=1; i<=firstSize;++i){
                if(getData().get(i)[type].equals(temp[k])) {
                    temp2.add(getRecordLine(i+1));
                    getData().set(i,buf);
                    break;
                }
            }
        }
        setData(temp2);
        write("PersonelFile.csv");

    }

    /**
     * Delete personel in Giving ID
     * If ID is not in List. Print Error Message.
     * @param _ID
     * @throws IOException
     */
    public void deletePersonel(String _ID) throws IOException {
        personelMap.remove(_ID);
    }

    /**
     * Giving Permission.
     * If ID is not in List. Print Error Message.
     * @param _ID Person ID
     * @param date Which Date
     * @throws IOException for write method
     */
    public void SetPermission(String _ID,String date) throws IOException {

        //ID listemizde var mı diye kontrol yapıyor.
        ArrayList<String> inList = new ArrayList<String>();
        for(int index=1; index<=getData().size();++index){
            if(getRecordLine(index)[0].equals(_ID)) {
                inList.add("In List");
                break;
            }
        }
        if(inList.size()==0) {
            System.err.printf("This %s ID is not in List ...", _ID);
            return;
        }
        int index;

        for(index=1; index<getData().size();++index){
            if(getRecordLine(index)[0].equals(_ID)) break;
        }
        getRecordLine(index)[12]=date ;
        setRecordLine(getRecordLine(index),index);
        write("PersonelFile.csv");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Map<String, Personel> getPersonelMap() {
        return personelMap;
    }
}
