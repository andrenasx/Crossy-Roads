package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.Gui;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHelpMenu;
import crossyroads.view.GuiMainMenu;

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
        Gui.COMMAND command = gui.getNextCommand();
        switch (command){
            case PLAY:
                GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
                appController.setCurrentState(new GameState(appController, new GuiGame(gameModel, gui.getScreen()), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
                break;
            case EXIT:
                appController.setCurrentState(new MenuState(appController, new GuiMainMenu(gui.getScreen())));
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
