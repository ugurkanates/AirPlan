package Airplan;
/**
 * Created by paypaytr on 4/24/18.
 */
public class Luggage {
    private final double kg_pound = 2.20462; // KG' exchange to multiple
    private int BAGGAGE_ID;
    private  double KG;
    private double LBS;
    private String TYPE; // 1 luggage,2 luggage combined etc
    /*
     Baggage Types

     1-Classic
     2-Roller
     3-Duffel
     4-Backpack
     5-TrashBag
     6-Guitar Case
     7-Pet Case
      String types comparison will be done .

     */

    public Luggage(int BAGGAGE_TEST,double KG_TEST,String TYPE_TEST){
        BAGGAGE_ID = BAGGAGE_TEST;
        KG = KG_TEST;
        LBS = KG_TEST *kg_pound;
        TYPE = TYPE_TEST;
    }
    public Luggage(){
        BAGGAGE_ID = 0;
        KG = 0;
        LBS = 0;
        TYPE = null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This Luggage Belongs to\n");
        sb.append("ID =");
        sb.append(BAGGAGE_ID);
        sb.append("\n Weight of This KG =");
        sb.append(KG);
        sb.append("--------- LBS:");
        sb.append(LBS);
        sb.append("\n Type of This = ");
        sb.append(TYPE);

       return sb.toString();


    }
    public int getBAGGAGE_ID() {
        return BAGGAGE_ID;
    }

    public void setBAGGAGE_ID(int BAGGAGE_ID) {
        this.BAGGAGE_ID = BAGGAGE_ID;
    }

    public double getKG() {
        return KG;
    }

    public void setKG(double KG) {
        this.KG = KG;
    }

    public double getLBS() {
        return LBS;
    }

    public void setLBS(double LBS) {
        this.LBS = LBS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
