package Airplan;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by paypaytr on 2/28/18.
 */
public class csv_luggage_write {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Airline Company,VIP,ToWhere,FromWhere,Date,BAGID,BAGKG,BAGTYPE";


    public static void writeCsvFile(String fileName, ArrayList<Passenger> konuklar) {


        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Passenger konuk : konuklar) {
                fileWriter.append(String.valueOf(konuk.getName()));
                fileWriter.append(COMMA_DELIMITER);

         //       fileWriter.append(konuk.getMyTicket().getCompany());
                fileWriter.append(COMMA_DELIMITER);

                if(konuk.getVIP().equals("vip"))
                    fileWriter.append("true");
                else
                    fileWriter.append("false");
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(konuk.getToWhere());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(konuk.getFromWhere());
                fileWriter.append(COMMA_DELIMITER);
          //      fileWriter.append(String.valueOf(konuk.getMyTicket().getTakeOffDay().toString()));
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(String.valueOf(konuk.getOwnPackage().getKG()));
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(String.valueOf(konuk.getOwnPackage().getBAGGAGE_ID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(konuk.getOwnPackage().getTYPE());
                fileWriter.append(NEW_LINE_SEPARATOR);
            


            }


            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

}




