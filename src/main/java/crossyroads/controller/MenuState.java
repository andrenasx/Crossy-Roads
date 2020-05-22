package crossyroads.controller;

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
        System.out.println(command);
        if (command == GuiMainMenu.COMMAND.PLAY) {
            appController.setCurrentState(new GameState(appController));
        }
    }
}
