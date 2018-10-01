package Airplan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by emre on 25.04.2018.
 */
public class Controller_manage_flight implements Initializable {

    private Scene lastScene;
    private Sound sound1;

    static public boolean audioPlayedManagement = false;
    static public boolean audioPlayedManagement1 = false;
    static public boolean audioPlayedManagement2 = false;



    @FXML
    DatePicker date;

    @FXML
    private ComboBox<String> sCombo= new ComboBox(), dCombo = new ComboBox<>(), flightType = new ComboBox<>(), comCombo = new ComboBox<>();

    private  ObservableList<String> airports = FXCollections.observableArrayList();
    private  ObservableList<String> fType = FXCollections.observableArrayList(), companyName = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!audioPlayedManagement) {
            sound1 = new Sound("sound/intro/management.wav");
            sound1.stop();
            sound1.play();
            audioPlayedManagement = true;
        }
        ArrayList temp = Main.graph.getAirports();
        Collections.sort(temp);
        airports.addAll(temp);

        sCombo.setItems(airports);
        new AutoCompleteComboBoxListener<>(sCombo);

        dCombo.setItems(airports);
        new AutoCompleteComboBoxListener<>(dCombo);

        temp = new ArrayList();
        temp.add("Normal");
        temp.add("Cargo");
        temp.add("Private");


        fType.addAll(temp);
        flightType.setItems(fType);

        temp = new ArrayList();
        temp.add("Turkish Airlines");
        temp.add("Pegasus");
        temp.add("Onur Air");


        companyName.addAll(temp);
        comCombo.setItems(companyName);

    }


    @FXML
    public void manageFlightStart(ActionEvent event) throws Exception{
        sound1 = new Sound("sound/misc_sound.wav");
        sound1.stop();
        sound1.play();

        Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();

        lastScene = ((Node)event.getSource()).getScene();
        if (!Main.windows.empty())
        {
            if (!Main.windows.peek().equals(lastScene))
                Main.windows.push(lastScene);
        }
        else
            Main.windows.push(lastScene);

        String fxml= null;
        String title=null;

        if (((Node) event.getSource()).getId().equals("createButton"))
        {
            fxml = "gui_fxml/create_new_flight.fxml";
            title = "Create Flight";
        }
        else if (((Node) event.getSource()).getId().equals("manageButton"))
        {
            fxml = "gui_fxml/manage_takingoff_landingon.fxml";
            title = "Take on/off";
        }



        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        newWindow.setTitle(title);

        newWindow.setScene(new Scene(root, 1280, 720));
        newWindow.show();
        if(!audioPlayedManagement1 && title.equals("Create Flight")) {
            sound1 = new Sound("sound/intro/createflight.wav");
            sound1.stop();
            sound1.play();
            audioPlayedManagement = true;
        }
        if(!audioPlayedManagement2 && title.equals("Take on/off")) {
            sound1 =  new Sound("sound/intro/manageflight.wav");
            sound1.stop();
            sound1.play();
            audioPlayedManagement = true;
        }

    }

    @FXML
    public void back(MouseEvent event){
        sound1 = new Sound("sound/back.wav");
        sound1.stop();
        sound1.play();
        Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();

        String id = ((Node) event.getSource()).getId();
        if (id.equals("backCreateFlight") || id.equals("backTakeOnOff"))
        {
            newWindow.setTitle("Managements of Flights");
        }
        else{
            newWindow.setTitle("Main Menu");
        }

        newWindow.setScene(Main.windows.pop());
        newWindow.show();
    }

    @FXML
    public void createCancel(ActionEvent event){


        Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Managements of Flights");

        String id = ((Node) event.getSource()).getId();
        if (id.equals("createButton"))
        {
            String source = sCombo.getValue(), dest = dCombo.getValue(), type = flightType.getValue(), company=comCombo.getValue();
            if ( source == null ||   dest== null ||  type== null || company == null || date.getValue() == null){
                msgBox("Please Enter all info!!");
            }else{
                if (source.equals(dest)){
                    msgBox("Source can't equal destination!!");
                }
                else
                {
                    LocalDate temp = date.getValue();

                    Main.graph.createFligth(source, dest, type, temp.toString(), company);
                    msgBox("Flight created!");
                }
            }
        }
        else
        {
            newWindow.setScene(Main.windows.pop());
            newWindow.show();
        }



    }


    public void firstUpButtonHandler(MouseEvent event){
        System.out.println("save button");
    }

    public void firstDownButtonHandler(MouseEvent event){
        System.out.println("save button");
    }
    public void secondDownButtonHandler(MouseEvent event){
        System.out.println("save button");
    }
    public void secondUpButtonHanler(MouseEvent event){
        System.out.println("save button");
    }


    private Stage dialogStage;

    public void msgBox(String labelInfo){
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

        Label lab_alert= new Label(labelInfo);
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

