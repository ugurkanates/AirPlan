package Airplan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Airplan.logIn.BinarySearchTree;
import Airplan.logIn.Personel;

import java.io.BufferedReader;
import java.io.FileReader;







import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import Airplan.logIn.BinarySearchTree;
import Airplan.logIn.Personel;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller_login {

    private Scene lastScene;


    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    @FXML
    private Label loginStatus;


    @FXML
    private AnchorPane anchorlogin;

    private Sound sound1;
    static public boolean audioPlayed = false;



    @FXML
    public void initialize(){
        if(!Main.isSplash)
            loadSplashScreen();



    }


    @FXML
    public void logIn(ActionEvent event) throws Exception{
    //************************************************************************************************************
        sound1 = new Sound("sound/click.wav");
        sound1.stop();
        sound1.play();


        String usersFile = "personel.csv";
        BinarySearchTree<Personel> users = new BinarySearchTree<Personel>();
        String line;
        String[] splittedLine;

        try {
            //get users from users.csv file and fill users arraylists
            BufferedReader reader = new BufferedReader(new FileReader(usersFile));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                splittedLine = line.split(",");
                users.add(new Personel(Integer.parseInt(splittedLine[0]),splittedLine[1],splittedLine[2],Integer.parseInt(splittedLine[3]),
                        splittedLine[4],splittedLine[5],splittedLine[6],splittedLine[7],splittedLine[8],Integer.parseInt(splittedLine[9]),splittedLine[10],splittedLine[11]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer id=0;
        try {
            id = Integer.parseInt(username.getText());
        }catch (NumberFormatException e){
            loginStatus.setText("Login Failed!! - Enter Id number !!");
            pass.setText("");
            return;
        }

        Personel currentPersonel = users.getById(id);
        //*****************************************************************************************************

        if(currentPersonel != null &&  currentPersonel.getPassword().equals(pass.getText()) ){

            Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("gui_fxml/login_start.fxml"));
            newWindow.setTitle("Main Menu");
            newWindow.setScene(new Scene(root, 1280, 720));
            newWindow.show();
            if(!audioPlayed) {
                sound1 = new Sound("sound/intro/menu.wav");
                sound1.stop();
                sound1.play();
                audioPlayed = true;
            }
        }
        else
        {
            loginStatus.setText("Login Failed!!");
            pass.setText("");
        }

    }

    @FXML
    public void forget(ActionEvent event){
        // some implment
        sound1 = new Sound("sound/click.wav");
        sound1.stop();
        sound1.play();
    }

    @FXML
    public void logIn_start(ActionEvent event) throws Exception{
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
        int which =0;

        sound1 = new Sound("sound/click.wav");
        sound1.stop();
        sound1.play();
        if (((Node) event.getSource()).getId().equals("luggageButton"))
        {
            fxml = "gui_fxml/luggage.fxml";
            title = "Luggage System";
            which =0;
        }
        else if (((Node) event.getSource()).getId().equals("flightButton"))
        {
            fxml = "gui_fxml/manage_of_flight.fxml";
            title = "Managements of Flights";
            which =1;
        }
        else if (((Node) event.getSource()).getId().equals("ticketButton"))
        {
            fxml = "gui_fxml/main_ticket.fxml";
            title = "Ticket System";
            which =2;
        }
        else if (((Node) event.getSource()).getId().equals("personelButton"))
        {
            fxml = "gui_fxml/manage_personal_start.fxml";
            title = "Personnel System";
            which =3;
        }


        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        newWindow.setTitle(title);
        newWindow.setScene(new Scene(root, 1280, 720));
        newWindow.show();

    }



    private void loadSplashScreen() {
        try {
            Main.isSplash = true;


            AnchorPane pane = FXMLLoader.load(getClass().getResource(("gui_fxml/splash_intro.fxml")));
            anchorlogin.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(5);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(10), pane);
            fadeOut.setFromValue(5);
            fadeOut.setToValue(4);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("gui_fxml/login.fxml")));
                    anchorlogin.getChildren().setAll(parentContent);
                    sound1 = new Sound("sound/intro/welcome.wav");
                    sound1.stop();
                    sound1.play();

                } catch (IOException ex) {
                    Logger.getLogger(Controller_login.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(Controller_login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
