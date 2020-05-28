package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiLost;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class LostState implements State{
    private AppController appController;
    private GuiLost gui;

    public LostState(AppController app, GuiLost gui){
        this.appController = app;
        this.gui = gui;
    }

    @Override
    public void step() throws IOException {
        gui.draw();
        GuiLost.COMMAND command = gui.getNextCommand();
        switch (command){
            case EXIT:
                appController.setEnd();
                break;
            case MENU:
                appController.setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));
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
