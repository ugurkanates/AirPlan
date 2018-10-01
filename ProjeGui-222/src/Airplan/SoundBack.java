package Airplan;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created by paypaytr on 5/30/18.
 */
public class SoundBack {
    public static SoundBack sound1 = new SoundBack("gui_fxml/menumusic.wav");


    private Clip clip;

    // Change file name to match yours, of course


    public SoundBack(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip == null) return;
        clip.stop();
    }

    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return clip.isActive();
    }
}
