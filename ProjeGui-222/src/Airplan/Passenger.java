package Airplan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paypaytr on 4/25/18.
 */
public class Passenger extends HumanAbstract {
    private Luggage ownPackage;
    private String VIP;
    private Ticket myTicket[] = new Ticket[2];
    private String fromWhere;
    private String toWhere;
    private String name;
    private String surname;

    public Passenger(){
        ownPackage = new Luggage();
        VIP = "";
        myTicket[0] = new Ticket();
        myTicket[1] = new Ticket();
        fromWhere = null;
        toWhere = null;
    }
    //1 WAY BILET ALMA
    // 0 = tekli bilet
    // ikilibilet te 0 = gidis 1 = donus

    public Map<String,String> getInfo(){
        Map<String,String > map = new HashMap<>();
        map.put("name",getName());
        map.put("surname",getSurname());
        map.put("vip", VIP);
        map.put("from",fromWhere);
        map.put("where",toWhere);
        return map;
    }

    //luggage CONSTRUCTOR ID =  PASSENGER ID OLCAK YANI = NEW LUGGAGE(PASS.INDEX,TİP,KİLO)

    public Passenger(Luggage myPack,String vip_yes,Ticket ticket,String where1,String where2,String name1
            ,String surname1){
        {
            ownPackage = myPack;
            VIP =  vip_yes;
            myTicket[0] = ticket;
            fromWhere = where1;
            toWhere = where2;
            name = name1;
            surname=surname1;

        }
    }
    public Passenger(Luggage myPack,String vip_yes,Ticket ticket,Ticket ticket2,String where1,String where2,String name1
            ,String surname1){
        {
            ownPackage = myPack;
            VIP =  vip_yes;
            myTicket[0] = ticket;
            myTicket[1] = ticket2;
            fromWhere = where1;
            toWhere = where2;
            name = name1;
            surname=surname1;
        }
    }
    public Luggage getOwnPackage() {
        return ownPackage;
    }

    public void setOwnPackage(Luggage ownPackage) {
        this.ownPackage = ownPackage;
    }

    public String  getVIP() {
        return VIP;
    }

    public void setVIP(String VIP) {
        this.VIP = VIP;
    }

    public Ticket[] getMyTickets() {
        return myTicket;
    }

    public void setMyTicket(Ticket[] myTickets) {
        this.myTicket = myTickets;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getToWhere() {
        return toWhere;
    }

    public void setToWhere(String toWhere) {
        this.toWhere = toWhere;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
