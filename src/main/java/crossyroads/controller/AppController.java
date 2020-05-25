package crossyroads.controller;

import crossyroads.controller.states.MenuState;
import crossyroads.controller.states.State;

import java.io.IOException;

public class AppController {
    private State currentState;

    public AppController() throws IOException {
        this.currentState = new MenuState(this);
    }

    public void start() throws IOException {
        while(true){
            currentState.step();
        }
    }

    public void setCurrentState(State state){
        currentState = state;
    }
}
