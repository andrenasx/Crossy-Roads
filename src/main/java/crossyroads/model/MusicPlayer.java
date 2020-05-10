package crossyroads.model;
//From https://www.youtube.com/watch?v=TErboGLHZGA

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {
    private Clip clip;
    private long clipTimePosition;


    public MusicPlayer(String musicPath) {
        try {
            File musicFile = new File(musicPath);

            if(musicFile.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clipTimePosition = 0;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pauseMusic(){
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void resumeMusic(){
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
    }

    public void stopMusic(){
        clip.stop();
    }
}
