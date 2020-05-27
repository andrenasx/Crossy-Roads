package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiWon;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class WonState implements State {
    private AppController appController;
    private GuiWon gui;

    public WonState(AppController app, GuiWon gui){
        this.appController = app;
        this.gui = gui;
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
                appController.setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));
                break;
            default:
                break;
        }
    }
}
