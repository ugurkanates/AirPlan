package Airplan;

import Airplan.flight_manage.ManagementFlights;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static Stack<Scene> windows;
    public static PersonelManagement personnel;
    public static ManagementFlights graph;
    public static Queue<Luggage> luggages;
    public static ArrayList<Passenger> passengers;

    public static Boolean isSplash = false;


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui_fxml/login.fxml"));

        try{
            loader.load();
        }catch (IOException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }

        windows = new Stack<>();
        personnel = new PersonelManagement();
        graph = new ManagementFlights();
        luggages = new LinkedList<>();
        passengers = new ArrayList<>();

        Controller_login login = loader.getController();
      //  login.initialize_objects(windows, graph, personnel);

        Parent root = loader.getRoot();
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, 1280, 720));
        //primaryStage.setResizable(true);
        primaryStage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("gui_fxml/login.fxml"));
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, 800, 600));
        //primaryStage.setResizable(true);
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
