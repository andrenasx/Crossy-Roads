package crossyroads.controller;

import crossyroads.controller.states.MenuState;
import crossyroads.controller.states.State;
import crossyroads.model.MusicPlayer;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class AppController {
    private State currentState;
    private boolean end;

    public AppController() throws IOException {
        this.currentState = new MenuState(this, new GuiMainMenu(ScreenFactory.getScreen()));
        this.end = false;
    }

    public void start() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/music.wav");
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
