package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiHelpMenu;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class HelpState implements State{
    private AppController appController;
    private GuiHelpMenu gui;

    public HelpState(AppController appController) throws IOException {
        this.appController = appController;
        this.gui = new GuiHelpMenu(ScreenFactory.getScreen());
    }


    @Override
    public void step() throws IOException {
        gui.draw();
        GuiHelpMenu.COMMAND command = gui.getNextCommand();
        System.out.println(command);
        switch (command){
            case PLAY:
                appController.setCurrentState(new GameState(appController));
                break;
            case BACK:
                appController.setCurrentState(new MenuState(appController));
                break;
            default:
                break;
        }
    }
}
