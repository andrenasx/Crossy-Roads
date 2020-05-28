package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiPauseMenu;
import crossyroads.view.ScreenFactory;

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
        //appController.getPlayer().pauseMusic();
        gui.draw();
        GuiPauseMenu.COMMAND command = gui.getNextCommand();
        switch (command){
            case RESUME:
                appController.setCurrentState(gameState);
                break;
            case MENU:
                appController.setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));
                break;
            default:
                break;
        }
        //appController.getPlayer().resumeMusic();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return getClass() == obj.getClass();
    }
}
