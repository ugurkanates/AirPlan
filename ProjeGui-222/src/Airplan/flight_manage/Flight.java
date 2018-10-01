package Airplan.flight_manage;

import java.io.Serializable;
import java.util.Vector;

public class Flight implements Serializable {

    String source, destination, flightType;
    String  flightNo, date;
    Vector<Integer> route;

    Flight(String source,String destination, String flightType, String flightNo, String date, Vector<Integer> route){
        this.source= source;
        this.destination = destination;
        this.flightType= flightType;
        this.flightNo = flightNo;
        this.route = route;
        this.date = date;
    }

}
