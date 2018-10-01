package Airplan;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by paypaytr on 4/25/18.
 */
public class Ticket {
    private String company;
    private String name;
    private String takeOffDay;
    private boolean reserved = false;
    private int seat;
    Ticket(){
        takeOffDay = "null";
        company = "NULL airports";
        name = "null";
        seat = 99;
        //Current Date: Sun 2004.07.18 at 04:14:09 PM PDT
        //  System.out.println("Current Date: " + ft.format(dNow));

    }
    Ticket(String companyOwn,String date1,String flightName,String seatNo){
        company = companyOwn;
        takeOffDay = date1;
        name = flightName;
        seat = Integer.parseInt(seatNo);


    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTakeOffDay() {
        return takeOffDay;
    }

    public void setTakeOffDay(String takeOffDay) {
        this.takeOffDay = takeOffDay;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
