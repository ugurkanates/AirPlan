package Airplan;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
    HW01
    --------
    Emre Behadir 141044021

 */
public class CVSData {


    private ArrayList<String[]> data;
    /**
     * Constructor for initilize data.
     */
    CVSData(){
        data = new ArrayList<>();
    }


    /** This functions add record to data.
     * @param element String array base items for new record.
     */
    public void addRecordLine(String[] element){
        data.add(element);
    }

    /** This function get exits data.
     * @return Return exist data.
     */
    public ArrayList<String[]> getData(){
        return data;
    }

    public void setData(ArrayList<String[]> _data){
        data = _data;
    }

    /** This function get a line with using index.
     * @param line İnteger line number.
     * @return Get line information.
     */
    public String[] getRecordLine(int line){

        try {
            if (line <= 0 || data.size() < line) {
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.print("Wrong line number !!!");
        }

        if (line == 0) {
            return data.get(line);
        }
        return data.get(line-1);

    }

    /** This function set old item using new datas.
     * @param elements String array base items for set record.
     * @param line İnteger line number.
     */
    public void setRecordLine(String[] elements, int line ){

        try {
            if (line <= 0 || data.size() < line) {
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.print("Wrong line number !!!");
        }

        if (line == 0) {
            data.set(line, elements);
        }
        data.set(line-1, elements);
        
    }


    /**
     * This function read library from cvs file.
     *
     * @param fileName It indicate which is the reading file.
     * @throws Exception This exception related for scanner.
     */
    public void read(String fileName) throws Exception{

        Scanner inputFile = null;
        try{

            inputFile = new Scanner(new FileInputStream(new File(fileName).getAbsolutePath()));

            inputFile.useDelimiter("\\s*\n\\s*");
        }
        catch (FileNotFoundException e){
            System.out.println("File is not exist or not opened !!!");
        }
        inputFile.next(); // ilk satırı okumamak için
        while(inputFile.hasNext()){

            String[] token = inputFile.next().split("\\s*,\\s*");
            //token.split("\\s*,\\s*");
            addRecordLine(token);

        }

        inputFile.close();
    }

    /**
     *
     * This function write library to cvs file.
     *
     * @param fileName It indicate which is the writing file.
     * @throws IOException This exeception related in file not open or not exist.
     */
    public void write(String fileName) throws IOException{

        FileWriter outputFile = new FileWriter(fileName);

        ArrayList<String[]> dataFile = getData();

        for (String[] items : dataFile) {
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(items) );
            outputFile.write(String.join(",",temp)+"\n");
        }

        outputFile.flush();
        outputFile.close();
    }


}
