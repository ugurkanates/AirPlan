package Airplan;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by paypaytr on 5/30/18.
 */
public class Sound {

    String file;
    Media sound;
    MediaPlayer player;

    public Sound(String file) {
        this.file = file;
        this.sound = new Media(new File(file).toURI().toString());
        this.player = new MediaPlayer(sound);
    }

    public void play(){
        player.play();
    }

    public void stop(){
        player.stop();
    }

    public void setVolume(double value){
        player.setVolume(value);
    }

    public double getVolume(){
        return player.getVolume();
    }
}