package crossyroads.controller;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.states.MenuState;
import crossyroads.controller.states.State;
import crossyroads.model.MusicPlayer;
import crossyroads.view.GuiMainMenu;

import java.io.IOException;

public class AppController {
    private State currentState;
    private boolean end;
    private MusicPlayer player;

    public AppController(TerminalScreen screen){
        this.currentState = new MenuState(this, new GuiMainMenu(screen));
        this.end = false;
        this.player = new MusicPlayer("src/main/resources/music.wav");
    }

    public void start() throws IOException {
        player.startMusic();

        while(!end){
            currentState.step();
        }

        player.stopMusic();
        System.exit(0);
    }

    public void setCurrentState(State state){
        currentState = state;
    }

    public void setEnd(){end = true;}

}
