package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiHelpMenu;

import java.io.IOException;

public class HelpState implements State{
    private AppController appController;
    private GuiHelpMenu gui;

    public HelpState(AppController appController) throws IOException {
        this.appController = appController;
        this.gui = new GuiHelpMenu();
    }


    @Override
    public void step() throws IOException {
        gui.draw();
        gui.getNextCommand();
        appController.setCurrentState(new MenuState(appController));
    }
}
