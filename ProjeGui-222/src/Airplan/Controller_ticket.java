package Airplan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by emre on 25.04.2018.
 */
public class Controller_ticket implements Initializable{

    private Sound sound1;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private CheckBox check_b = new CheckBox(), isVip=new CheckBox();

    @FXML
    private void is_2ways(){
        if (check_b.isSelected()){
            fromCombo2.setVisible(true);
            toCombo2.setVisible(true);
            endDatePicker.setVisible(true);
        }else {
            fromCombo2.setVisible(false);
            toCombo2.setVisible(false);
            endDatePicker.setVisible(false);
        }
    }



    @FXML
    private ComboBox<String>    fromCombo1 = new ComboBox<>(),
                                toCombo1   = new ComboBox<>(),
                                fromCombo2 = new ComboBox<>(),
                                toCombo2   = new ComboBox<>(),
                                chooseFlightCombo = new ComboBox<>(),
                                lugCombo = new ComboBox<>(),
                                companyCombo=new ComboBox<>(),
                                seatCombo = new ComboBox<>();

    @FXML
    TextField lugKg, name, surname;

    private ObservableList<String> airports = FXCollections.observableArrayList();
    private ObservableList<String> lugType = FXCollections.observableArrayList();
    private ObservableList<String> companies = FXCollections.observableArrayList();
    private ObservableList<String> seat = FXCollections.observableArrayList();
    private ObservableList<String> flights = FXCollections.observableArrayList();

    static public boolean audioPlayedTicket = false;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!audioPlayedTicket) {
            sound1 = new Sound("sound/intro/menu.wav");
            sound1.stop();
            sound1.play();
            audioPlayedTicket = true;
        }
        ArrayList temp = Main.graph.getAirports();
        Collections.sort(temp);
        airports.addAll(temp);

        fromCombo1.setItems(airports);
        new AutoCompleteComboBoxListener<>(fromCombo1);

        toCombo1.setItems(airports);
        new AutoCompleteComboBoxListener<>(toCombo1);


        fromCombo2.setItems(airports);
        new AutoCompleteComboBoxListener<>(fromCombo2);

        toCombo2.setItems(airports);
        new AutoCompleteComboBoxListener<>(toCombo2);


        fromCombo2.setVisible(false);
        toCombo2.setVisible(false);
        endDatePicker.setVisible(false);


        temp = new ArrayList();
        temp.add("Classic");
        temp.add("Roller");
        temp.add("Duffel");
        temp.add("Backpack");
        temp.add("Pet Case");
        temp.add("Guitar Case");
        temp.add("Clothes");


        lugType.addAll(temp);
        lugCombo.setItems(lugType);
        new AutoCompleteComboBoxListener<>(lugCombo);

        temp = new ArrayList();
        temp.add("Turkish Airlines");
        temp.add("Pegasus");
        temp.add("Onur Air");

        companies.addAll(temp);
        companyCombo.setItems(companies);

        temp = new ArrayList();
        for (int i = 0; i < 120 ; ++i) {
            temp.add(i+1);
        }

        seat.addAll(temp);
        seatCombo.setItems(seat);
        new AutoCompleteComboBoxListener<>(seatCombo);



        temp = Main.graph.getAirports();

        ArrayList<String > temp2 = new ArrayList();
        for (int i = 0; i < temp.size() ; i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (i!=j){
                    temp2.add((String) temp.get(i)+"/"+temp.get(j));
                }
            }
        }
        flights.addAll(temp2);
        chooseFlightCombo.setItems(flights);
    }






    @FXML
    public void book(ActionEvent event){
        String  from   = fromCombo1.getValue(),
                to     = toCombo1.getValue(),
                from2  = fromCombo2.getValue(),
                to2    = toCombo2.getValue(),
                flight = chooseFlightCombo.getValue(),
                lugType= lugCombo.getValue(),
                lugKg  = this.lugKg.getText(),
                name   = this.name.getText(),
                surname= this.surname.getText(),
                company =companyCombo.getValue(),
                seat = seatCombo.getValue();

        String isVip="";

        if (from == null || to == null  || flight == null || lugType == null || lugKg.isEmpty() || name.isEmpty() ||
          surname.isEmpty() || company == null || seat == null || startDatePicker.getValue() == null)
        {
            msgBox("Please select all info !!");
        }
        else
        {
            if (check_b.isSelected() && (from2 == null || to2 == null  || endDatePicker.getValue() == null)){
                msgBox("Please select all info !!");
            }
            else
            {
                if (this.isVip.isSelected()){
                    isVip="vip";
                }
                if (from.equals(to) || (from2 !=null && from2.equals(to2))){
                    msgBox("Source can't equal destination!!");
                }
                else
                {
                    LocalDate localDate = startDatePicker.getValue();
                    String startDate = localDate.toString();

                    localDate = endDatePicker.getValue();




                    if (!check_b.isSelected())
                        Main.passengers.add(new Passenger(null, isVip, new Ticket(company,startDate,flight,seat), from,to,name,surname));
                    else
                    {
                        String endDate= localDate.toString();
                        Main.passengers.add(new Passenger(null, isVip, new Ticket(company,startDate,flight,seat), new Ticket(company, endDate,flight,seat), from2,to2,name,surname));
                    }

                    msgBox("Process is done!");
                }

            }

        }



     }


    private Stage dialogStage;

    public void msgBox(String labelinfo){
        sound1 = new Sound("sound/negative.wav");
        sound1.stop();
        sound1.play();
        dialogStage = new Stage();
        GridPane grd_pan = new GridPane();
        grd_pan.setAlignment(Pos.CENTER);
        grd_pan.setHgap(10);
        grd_pan.setVgap(10);//pading
        Scene scene =new Scene(grd_pan,400,200);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Attention");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Label lab_alert= new Label(labelinfo);
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

}
