package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.*;

import java.io.IOException;

public class HighscoreState implements State {
    private AppController appController;
    private GuiHighscoreMenu gui;

    public HighscoreState(AppController appController, GuiHighscoreMenu gui) {
        this.appController = appController;
        this.gui = gui;
    }
    @Override
    public void step() throws IOException {
        gui.draw();
        GuiHighscoreMenu.COMMAND command = gui.getNextCommand();
        switch (command){
            case PLAY:
                GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
                appController.setCurrentState(new GameState(appController, new GuiGame(gameModel, ScreenFactory.getScreen()), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
                break;
            case BACK:
                appController.setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));
                break;
            default:
                break;
        }
    }
}