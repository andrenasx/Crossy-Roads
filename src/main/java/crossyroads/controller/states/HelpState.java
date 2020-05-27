package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHelpMenu;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class HelpState implements State{
    private AppController appController;
    private GuiHelpMenu gui;

    public HelpState(AppController appController, GuiHelpMenu gui) {
        this.appController = appController;
        this.gui = gui;
    }


    @Override
    public void step() throws IOException {
        gui.draw();
        GuiHelpMenu.COMMAND command = gui.getNextCommand();
        System.out.println(command);
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
