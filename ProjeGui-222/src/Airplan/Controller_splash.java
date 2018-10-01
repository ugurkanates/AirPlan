package Airplan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by paypaytr on 5/30/18.
 */
public class Controller_splash implements Initializable {
    @FXML
    private MediaView media;

    private MediaPlayer mPlayer;
    private static final String MEDIA_URL ="gui_fxml/baslangic.mp4";
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
        mPlayer.setAutoPlay(true);
        media.setMediaPlayer(mPlayer);


    }

}
