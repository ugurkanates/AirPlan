package Airplan;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by paypaytr on 2/28/18.
 */
public class csv_luggage_read {

    //Delimiter used in CSV file
    private static final String FILE_HEADER = "Airline Company,VIP,ToWhere,FromWhere,Date,BAGID,BAGKG,BAGTYPE";
    private static final String COMMA_DELIMITER = ",";
    //namede name+surname boslukla split et atarken
    //bos gelen room no -1 ata yada bisi ata unutma


    //Student attributes index
    private static final int AIRLINE = 0;
    private static final int VIP= 1;
    private static final int TOWHERE = 2;
    private static final int FROMWHERE = 3;
    private static final int DATE = 4;
    private static final int BAGID = 5;
    private static final int BAGKG = 6;
    private static final int BAGTYPE = 7;


 





    public static void readCsvFile(String fileName,ArrayList<Passenger> konuklar) {

        BufferedReader fileReader = null;

        try {

            //Create a new list of student to be filled by CSV file data

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new student object and fill his  data
                    Passenger konuk = new Passenger();
                    // Name Surname Tokens ->
          //          konuk.getMyTicket().setCompany(tokens[AIRLINE]);

                    if(tokens[VIP].equals("true"))
                        konuk.setVIP("vip");
                    else
                        konuk.setVIP("");
                    konuk.setToWhere(tokens[TOWHERE]);
                    konuk.setFromWhere(tokens[FROMWHERE]);

                    
                   DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                   //BUNU YAPMAK COK ZORDU -> oh be thanks

          //          konuk.getMyTicket().setTakeOffDay(format.parse(tokens[DATE]));
                    konuk.getOwnPackage().setBAGGAGE_ID(Integer.parseInt(tokens[BAGID]));
                    konuk.getOwnPackage().setKG(Double.parseDouble(tokens[BAGKG]));
                     konuk.getOwnPackage().setTYPE(tokens[BAGTYPE]);




                    konuklar.add(konuk);
                }
            }


        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }
}
