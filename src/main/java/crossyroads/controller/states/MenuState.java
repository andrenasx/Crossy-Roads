package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.*;

import java.io.IOException;

public class MenuState implements State{
    private AppController appController;
    private GuiMainMenu gui;

    public MenuState(AppController ap, GuiMainMenu gui) {
        this.appController = ap;
        this.gui = gui;
    }

    @Override
    public void step() throws IOException {
        gui.draw();
        GuiMainMenu.COMMAND command = gui.getNextCommand();
        switch (command){
            case PLAY:
                GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
                appController.setCurrentState(new GameState(appController, new GuiGame(gameModel, gui.getScreen()), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
                break;
            case HELP:
                appController.setCurrentState(new HelpState(appController, new GuiHelpMenu(gui.getScreen())));
                break;
            case HIGHSCORES:
                appController.setCurrentState(new HighscoreState(appController, new GuiHighscoreMenu(gui.getScreen())));
                break;
            case EXIT:
                appController.setEnd();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return getClass() == obj.getClass();
    }
}
