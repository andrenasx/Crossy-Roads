package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiWon;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class WonState implements State {
    private AppController appController;
    private GuiWon gui;

    public WonState(AppController app, int score, int health, int steps) throws IOException {
        this.appController = app;
        this.gui = new GuiWon(score, health, steps, ScreenFactory.getScreen());
    }
    @Override
    public void step() throws IOException {
        gui.draw();
        GuiWon.COMMAND command = gui.getNextCommand();
        switch (command){
            case EXIT:
                System.exit(0);
                break;
            case MENU:
                appController.setCurrentState(new MenuState(appController));
                break;
            default:
                break;
        }
    }
}
