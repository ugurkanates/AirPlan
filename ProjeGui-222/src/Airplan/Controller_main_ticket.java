package Airplan;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Map;

public class Controller_main_ticket
{

    private Scene lastScene;
    private Sound sound1;
    static public boolean audioPlayedTicket = false;

    @FXML
    private TableView<TableIfo> table = new TableView<>();

    @FXML
    private TextField passengerText = new TextField();



    public void initialize(){
        setTableInfo();
        if(!audioPlayedTicket) {
            sound1 = new Sound("sound/intro/ticket.wav");
            sound1.stop();
            sound1.play();
            audioPlayedTicket = true;
        }
    }

    @FXML
    public void ticket_start(ActionEvent event) throws Exception{
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

        if (((Node) event.getSource()).getId().equals("book"))
        {
            fxml = "gui_fxml/ticket.fxml";
            title = "Book Ticket";
        }
        else    // look
        {
            fxml = "gui_fxml/look_passenger.fxml";
            title = "Look Passenger";
        }


        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        newWindow.setTitle(title);
        newWindow.setScene(new Scene(root, 1280, 720));
        newWindow.show();

    }

    @FXML
    public void back(MouseEvent event){
        sound1 = new Sound("sound/back.wav");
        sound1.stop();
        sound1.play();

        String title="";
        if (((Node) event.getSource()).getId().equals("back2"))
        {
            title = "Ticket System";
        }
        else
        {
            title = "Main Menu";
        }

        Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle(title);

        newWindow.setScene(Main.windows.pop());
        newWindow.show();
    }




    private ObservableList<TableIfo> masterData;

    private void setTableInfo(){
        TableColumn<TableIfo, String> nameCol = new TableColumn<>("NAME");
        nameCol.setMinWidth(175);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TableIfo, String> surCol = new TableColumn<>("SURNAME");
        surCol.setMinWidth(150);
        surCol.setCellValueFactory(new PropertyValueFactory<>("surname"));


        TableColumn<TableIfo, String> fromCol = new TableColumn<>("FROM");
        fromCol.setMinWidth(200);
        fromCol.setCellValueFactory(new PropertyValueFactory<>("fromWhere"));

        TableColumn<TableIfo, String> toCol = new TableColumn<>("TO");
        toCol.setMinWidth(200);
        toCol.setCellValueFactory(new PropertyValueFactory<>("toWhere"));

        TableColumn<TableIfo, String> vipCol = new TableColumn<>("VIP");
        vipCol.setMinWidth(100);
        vipCol.setCellValueFactory(new PropertyValueFactory<>("VIP"));



        masterData = getPersonels();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<TableIfo> filteredData = new FilteredList(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        passengerText.textProperty().addListener((observable, oldValue, newValue) -> {
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
        SortedList<TableIfo> sortedData = new SortedList(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());


        table.setItems(sortedData);
        table.getColumns().addAll(nameCol, surCol , fromCol, toCol,vipCol);
    }



    public class TableIfo{

        private String fromWhere;
        private String toWhere;
        private String name;
        private String surname,vip;


        public TableIfo(String name, String surname, String from, String  to, String vip){
            this.name = name;
            this.surname = surname;
            this.fromWhere= from;
            this.toWhere= to;
            this.vip = vip;
        }

        public String getFromWhere() {
            return fromWhere;
        }

        public String getToWhere() {
            return toWhere;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getVip() {
            return vip;
        }
    }


    public ObservableList<TableIfo> getPersonels() {
        ObservableList<TableIfo> temp = FXCollections.observableArrayList();

        Map<String, String> map;

        for (Passenger val : Main.passengers
                ) {
            map = val.getInfo();
            temp.add(new TableIfo(map.get("name"), map.get("surname"), map.get("from"),
                    map.get("where"), map.get("vip")));
        }


        return temp;
    }
}
