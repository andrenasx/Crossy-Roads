package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiMainMenu;

import java.io.IOException;

public class MenuState implements State{
    private AppController appController;
    private GuiMainMenu gui;

    public MenuState(AppController ap) throws IOException {
        this.appController = ap;
        this.gui = new GuiMainMenu();
    }

    @Override
    public void step() throws IOException {
        gui.draw();
        GuiMainMenu.COMMAND command = gui.getNextCommand();
        switch (command){
            case PLAY:
                appController.setCurrentState(new GameState(appController));
                break;
            case HELP:
                appController.setCurrentState(new HelpState(appController));
                break;
            case EXIT:
                System.exit(0);
            default:
                break;
        }
    }
}
