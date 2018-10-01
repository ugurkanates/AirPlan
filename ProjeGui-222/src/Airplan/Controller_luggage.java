package Airplan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by emre on 25.04.2018.
 */
public class Controller_luggage implements Initializable {

    private Sound sound1;

    @FXML
    public Label luggageID, luggageKG, luggageLBS, luggageType;

    @FXML
    public ImageView luggagePic;


    private Luggage current,before; // STATIC DEMO PURPOSELOSLNY
    private int size;
    private Image obj1,obj2,obj3,obj4,obj5,obj6,obj7;
    static public boolean audioPlayedLuggage = false;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //size = Main.luggages.size();
        if(!audioPlayedLuggage) {
            sound1 = new Sound("sound/intro/luggage.wav");
            sound1.stop();
            sound1.play();
            audioPlayedLuggage = true;
        }
        testDemo();
    }


    public Image returnWhich(String type){
        if(type.equals("Classic"))
            return obj1;
        if (type.equals("Roller"))
            return obj2;
        if(type.equals("Duffel"))
            return obj3;
        if(type.equals("Backpack"))
            return obj4;
        if(type.equals("Pet Case"))
            return obj5;
        if(type.equals("Guitar Case"))
            return obj6;
        if(type.equals("Clothes"))
            return obj7;
        return null;
    }

    public void testDemo(){
        obj1 = new Image("CantaResimler/1.jpeg");
        obj2 = new Image("CantaResimler/2.jpg");
        obj3 = new Image("CantaResimler/3.jpg");
        obj4 = new Image("CantaResimler/4.jpeg");
        obj5 = new Image("CantaResimler/5.jpg");
        obj6 = new Image("CantaResimler/6.jpg");
        obj7 = new Image("CantaResimler/7.jpg");

        Luggage test = new Luggage(2018,5,"Classic");
        /*
        Main.luggages.enqueue(test);
        Main.luggages.enqueue(new Luggage(2017,19,"Roller"));
        Main.luggages.enqueue(new Luggage(2016,2,"Duffel"));
        Main.luggages.enqueue(new Luggage(2015,43,"Backpack"));
        Main.luggages.enqueue(new Luggage(2014,77,"Pet Case"));
*/
        //  FOR MOVING elements
        Main.luggages.add(new Luggage(2018,7,"Classic"));
        Main.luggages.add(new Luggage(2017,19,"Roller"));
        Main.luggages.add(new Luggage(2016,20,"Duffel"));
        Main.luggages.add(new Luggage(2015,4,"Backpack"));
        Main.luggages.add(new Luggage(2014,34,"Pet Case"));
        Main.luggages.add(new Luggage(2013,5.7,"Guitar Case"));
        Main.luggages.add(new Luggage(2012,7,"Clothes"));




        luggagePic.setImage(obj1);
        luggageID.setText(String.valueOf(test.getBAGGAGE_ID()));
        luggageKG.setText(String.valueOf(test.getKG()));
        luggageLBS.setText(String.valueOf(test.getLBS()));
        luggageType.setText(test.getTYPE());




        /*
        First objeleri set et !!!
         */
    }

    @FXML
    public void nextPrev(MouseEvent event){

        sound1 = new Sound("sound/click_2.wav");
        sound1.stop();
        sound1.play();

        String id = ((Node) event.getSource()).getId();

        if (id.equals("luggageNext"))
        {
            Luggage temp = Main.luggages.remove();
            Main.luggages.add(temp);
            current = Main.luggages.peek();

        }
        else    // prev
        {
            for(int i=0;i<Main.luggages.size()-1;i++){
                Luggage temp = Main.luggages.remove();
                Main.luggages.add(temp);

            }
            current = Main.luggages.peek();
        }
        luggagePic.setImage(returnWhich(current.getTYPE()));
        luggageID.setText(String.valueOf(current.getBAGGAGE_ID()));
        luggageKG.setText(String.valueOf(current.getKG()));
        luggageLBS.setText(String.valueOf(current.getLBS()));
        luggageType.setText(current.getTYPE());
    }

    @FXML
    public void take(ActionEvent event){
        // take luggage, dequeue item, set new luggage
       // Main.luggages.dequeue();
        // this element will be removed.

        // take luggage, dequeue item, set new luggage
        sound1 = new Sound("sound/positive.wav");
        sound1.stop();
        sound1.play();
        System.out.println("test dur");
        Luggage temp2 =Main.luggages.remove();
        // this element will be removed.
        current = Main.luggages.remove();

        Main.luggages.add(current);




        for(int i=0;i<Main.luggages.size();i++){
            System.out.printf("-> %d \n",Main.luggages.size());
            Luggage temp = Main.luggages.remove();
            Main.luggages.add(temp);
        }


        // next luggage
        luggagePic.setImage(returnWhich(current.getTYPE()));
        luggageID.setText(String.valueOf(current.getBAGGAGE_ID()));
        luggageKG.setText(String.valueOf(current.getKG()));
        luggageLBS.setText(String.valueOf(current.getLBS()));
        luggageType.setText(current.getTYPE());

    }
    private Stage dialogStage;

    public void msgBox(String windowName ,String title){
        dialogStage = new Stage();
        GridPane grd_pan = new GridPane();
        grd_pan.setAlignment(Pos.CENTER);
        grd_pan.setHgap(10);
        grd_pan.setVgap(10);//pading
        Scene scene =new Scene(grd_pan,640,320);
        dialogStage.setScene(scene);
        dialogStage.setTitle(windowName);
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Label lab_alert= new Label(title);
        grd_pan.add(lab_alert, 0, 1);

        Button btn_ok = new Button("    OK    ");
        btn_ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                dialogStage.hide();

            }
        });
        grd_pan.add(btn_ok, 0, 2);

        dialogStage.show();

    }

    @FXML
    public void help(ActionEvent event){
        sound1 = new Sound("sound/negative.wav");
        sound1.stop();
        sound1.play();

        msgBox("HELP","I NEED HELP ! \n " +
                "If you think you need help with our system\n"+
        "Please MAIL ADMINISTRATOR info@airplane.com");

    }


    @FXML
    public void back(MouseEvent event){
        sound1 = new Sound("sound/back.wav");
        sound1.stop();
        sound1.play();

        Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Main Menu");

        newWindow.setScene(Main.windows.pop());
        newWindow.show();
    }
}