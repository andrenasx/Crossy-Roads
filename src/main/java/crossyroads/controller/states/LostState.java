package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiLost;

import java.io.IOException;

public class LostState implements State{
    private AppController appController;
    private GuiLost gui;

    public LostState(AppController app, int level) throws IOException {
        this.appController = app;
        this.gui = new GuiLost(level);
    }

    @Override
    public void step() throws IOException {
        gui.draw();
        GuiLost.COMMAND command = gui.getNextCommand();
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
