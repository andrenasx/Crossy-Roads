package crossyroads.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private Clip clip;

    public MusicPlayer(String musicPath) {
        try {
            File musicFile = new File(musicPath);

            if(musicFile.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic(){
        clip.stop();
    }
}
