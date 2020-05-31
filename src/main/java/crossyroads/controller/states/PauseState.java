package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.Gui;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiPauseMenu;

import java.io.IOException;

public class PauseState implements State{
    private AppController appController;
    private GuiPauseMenu gui;
    private GameState gameState;

    public PauseState(AppController appController, GuiPauseMenu gui, GameState gameState) {
        this.appController = appController;
        this.gui = gui;
        this.gameState = gameState;
    }

    @Override
    public void step() throws IOException {
        gui.draw();
        Gui.COMMAND command = gui.getNextCommand();
        switch (command){
            case PLAY:
                appController.setCurrentState(gameState);
                break;
            case MENU:
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
