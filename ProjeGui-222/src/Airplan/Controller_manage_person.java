package Airplan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by emre on 25.04.2018.
 */
public class Controller_manage_person{


    private Scene lastScene;

    @FXML
    private Label listInfo;
    @FXML
    private TextField textPermission;





    //------------------------ ADD PERSONEL
    @FXML
    private TextField textPerAddID;
    @FXML
    private TextField textPerAddName;
    @FXML
    private TextField textPerAddAge;
    @FXML
    private TextField textPerAddSurname;
    @FXML
    private TextField textPerAddJop;
    @FXML
    private TextField textPerAddSalary;
    @FXML
    private TextField textPerAddBlood;
    @FXML
    private TextField textPerAddGender;
    @FXML
    private TextField textPerAddNational;
    @FXML
    private TextField textPerAddTel;

    @FXML
    private TextField textPerAddGraduate;

    @FXML
    private TextField textPerAddExp;

    @FXML
    private Label errorAdd= new Label();
    static public boolean audioPlayedPerson = false;


//------------------------ END ADD PERSONEL

//------------------- get permission
    @FXML
    private TextField permissionID;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Label permissionErr=new Label();


//----------------- end permission

    @FXML
    private TableView<TableIfo> tablePersonalRemove= new TableView<TableIfo>();;

    @FXML
    private TextField textPersonalRemove = new TextField();


    private Sound sound1;

    // @FXML
  //  private Button buttonGetInfo;

    @FXML
    public void initialize(){
        if(!audioPlayedPerson) {
            sound1 = new Sound("sound/intro/personal.wav");
            sound1.stop();
            sound1.play();
            audioPlayedPerson = true;
        }
        errorAdd.setVisible(false);
        permissionErr.setVisible(false);

        setTableInfo();

    }


    @FXML
    public void managePersonStart(ActionEvent event) throws Exception {
        sound1 = new Sound("sound/positive.wav");
        sound1.stop();
        sound1.play();
        Stage newWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        lastScene = ((Node) event.getSource()).getScene();
        if (!Main.windows.empty()) {
            if (!Main.windows.peek().equals(lastScene))
                Main.windows.push(lastScene);
        } else
            Main.windows.push(lastScene);

        String fxml = null;
        String title = null;

        if (((Node) event.getSource()).getId().equals("registerButton")) {
            fxml = "gui_fxml/manage_personal_start_add_screen.fxml";
            title = "Register";
        } else if (((Node) event.getSource()).getId().equals("deleteButton")) {
            fxml = "gui_fxml/manage_personal_start_remove_screen.fxml";
            title = "Remove";
        } else if (((Node) event.getSource()).getId().equals("infoButton")) {
            fxml = "gui_fxml/manage_personal_start_info_screen.fxml";
            title = "Info";
        } else if (((Node) event.getSource()).getId().equals("holidayButton")) {
            fxml = "gui_fxml/manage_personal_start_permission_screen.fxml";
            title = "Get Permission";
        }




        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        newWindow.setTitle(title);
        newWindow.setScene(new Scene(root, 1280, 720));
        newWindow.show();

    }

    @FXML
    public void back(MouseEvent event) {
        sound1 = new Sound("sound/back.wav");
        sound1.stop();
        sound1.play();

        Stage newWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String id = ((Node) event.getSource()).getId();
        if (id.equals("backPersonStart")) {
            newWindow.setTitle("Main Menu");
        } else {
            newWindow.setTitle("Personnel System");
        }


        newWindow.setScene(Main.windows.pop());
        newWindow.show();
    }

    public void saveButtonAddHandler(MouseEvent event) throws Exception {
        sound1 = new Sound("sound/misc_menu_4.wav");
        sound1.stop();
        sound1.play();

        if (textPerAddName.getText().isEmpty() || textPerAddID.getText().isEmpty() || textPerAddSurname.getText().isEmpty() ||
                textPerAddAge.getText().isEmpty() || textPerAddGender.getText().isEmpty() || textPerAddTel.getText().isEmpty() ||
                textPerAddNational.getText().isEmpty() || textPerAddBlood.getText().isEmpty() || textPerAddExp.getText().isEmpty() ||
                textPerAddGraduate.getText().isEmpty() || textPerAddJop.getText().isEmpty() || textPerAddSalary.getText().isEmpty()){

            msgBox("Attention","Please enter all info!!");
        }
        else
        {
            Personel newPersonel = new Personel(textPerAddID.getText(),textPerAddName.getText(),textPerAddSurname.getText(),
                    textPerAddAge.getText(),textPerAddGender.getText(), textPerAddTel.getText(),textPerAddNational.getText(),
                    textPerAddBlood.getText(),textPerAddExp.getText(), textPerAddGraduate.getText(), textPerAddJop.getText(),
                    textPerAddSalary.getText(),"0");


            if (Main.personnel.add(newPersonel)){
                msgBox("Success","Item is added!");


            }
            else
            {
                msgBox("Fail","Item is already in list!");
            }
        }



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

    public void getInfoButtonHandler(MouseEvent event) {
        sound1 = new Sound("sound/sharp_echo.wav");
        sound1.stop();
        sound1.play();
        try {
            msgBox("Person", Main.personnel.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStartPermHandler(MouseEvent event) throws Exception {

        if (permissionID.getText().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue()== null){
            msgBox("Attention","Please enter all info!!");
        }
        else{
            String id = permissionID.getText();

            LocalDate localDate = startDatePicker.getValue();
            String startDate = localDate.toString();

            localDate = endDatePicker.getValue();

            String endDate= localDate.toString();
            String time = startDate+"/"+endDate;

            System.out.println(time);

            try {
                Main.personnel.SetPermission(id, time);
            } catch (IOException e) {
                e.printStackTrace();
            }

            msgBox("Succes","All is successfully done !");
        }

        Map map = Main.personnel.getPersonelMap();

        System.out.println(map.toString());

    }
    @FXML
    public void getButtonPersonalRemoveHandler(MouseEvent event) {

        TableIfo info = tablePersonalRemove.getSelectionModel().getSelectedItem();
        if (info == null){
            msgBox("Failure","Please select item !!");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",  ButtonType.NO, ButtonType.YES);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                masterData.remove(info);
                try {
                    Main.personnel.deletePersonel(info.getID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private ObservableList<TableIfo> masterData;

    private void setTableInfo(){
        TableColumn<TableIfo, String> idCol = new TableColumn<>("ID");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<TableIfo, String> nameCol = new TableColumn<>("NAME");
        nameCol.setMinWidth(175);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<TableIfo, String> surCol = new TableColumn<>("SURNAME");
        surCol.setMinWidth(150);
        surCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));

        TableColumn<TableIfo, String> ageCol = new TableColumn<>("AGE");
        ageCol.setMinWidth(75);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn<TableIfo, String> genCol = new TableColumn<>("GENDER");
        genCol.setMinWidth(50);
        genCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));

        TableColumn<TableIfo, String> telCol = new TableColumn<>("TEL NO");
        telCol.setMinWidth(100);
        telCol.setCellValueFactory(new PropertyValueFactory<>("TelephoneNumber"));

        TableColumn<TableIfo, String> natCol = new TableColumn<>("NATIONALITY");
        natCol.setMinWidth(115);
        natCol.setCellValueFactory(new PropertyValueFactory<>("Nation"));

        TableColumn<TableIfo, String> blodCol = new TableColumn<>("BLOOD GROUP");
        blodCol.setMinWidth(125);
        blodCol.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));

        TableColumn<TableIfo, String> workCol = new TableColumn<>("JOB");
        workCol.setMinWidth(135);
        workCol.setCellValueFactory(new PropertyValueFactory<>("WorkingStyle"));

        TableColumn<TableIfo, String> salCol = new TableColumn<>("SALARY");
        salCol.setMinWidth(100);
        salCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));



        masterData = getPersonels();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<TableIfo> filteredData = new FilteredList(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        textPersonalRemove.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<TableIfo> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tablePersonalRemove.comparatorProperty());


        tablePersonalRemove.setItems(sortedData);
        tablePersonalRemove.getColumns().addAll(idCol, nameCol, surCol, ageCol, genCol, salCol, workCol, blodCol, natCol, telCol);
    }



    public class TableIfo{

        private String ID, Name, Surname, Age, Gender, TelephoneNumber, Nation, BloodGroup, WorkingStyle, Salary;


        public TableIfo(String _ID, String _Name, String _Surname, String _Age, String _Gender, String _TelephoneNumber,
                        String _Nation, String _BloodGroup, String _WorkingStyle, String _Salary){
            this.ID = _ID;
            this.Name = _Name;
            this.Surname = _Surname;
            this.Age = _Age;
            this.Gender = _Gender;
            this.TelephoneNumber = _TelephoneNumber;
            this.Nation = _Nation;
            this.BloodGroup = _BloodGroup;
            this.WorkingStyle = _WorkingStyle;
            this.Salary = _Salary;
        }

        public String getID() {
            return ID;
        }

        public String getName() {
            return Name;
        }

        public String getSurname() {
            return Surname;
        }

        public String getAge() {
            return Age;
        }

        public String getGender() {
            return Gender;
        }

        public String getTelephoneNumber() {
            return TelephoneNumber;
        }

        public String getNation() {
            return Nation;
        }

        public String getBloodGroup() {
            return BloodGroup;
        }

        public String getWorkingStyle() {
            return WorkingStyle;
        }

        public String getSalary() {
            return Salary;
        }
        @Override
        public String toString() {
            return ID+" "+Name+" "+Surname+" "+Age+" "+Gender+" "+TelephoneNumber+" "+Nation+" "+BloodGroup+" "+WorkingStyle+" "+Salary ;
        }

    }


    public ObservableList<TableIfo> getPersonels(){
        ObservableList<TableIfo> pers = FXCollections.observableArrayList();

        Map<String,Personel> map = Main.personnel.getPersonelMap();

        if (map != null){
            for (Map.Entry<String, Personel> entry : map.entrySet())
            {
                Map<String, String> map1 = entry.getValue().getInfo();
                //map1.values();

                pers.add(new TableIfo(map1.get("id"), map1.get("name"),
                        map1.get("surname"), map1.get("age"),map1.get("gender"), map1.get("tel"),
                        map1.get("nation"), map1.get("blood"), map1.get("job"), map1.get("salary")));
            }
        }

        return pers;

    }
}
