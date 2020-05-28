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
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MenuStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiMainMenu gui = mock(GuiMainMenu.class);

        MenuState menuState = new MenuState(appController, gui);

        //Exit Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.EXIT);
        menuState.step();
        verify(appController, times(1)).setEnd();

        //Help Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.HELP);
        menuState.step();
        verify(appController, times(1)).setCurrentState(new HelpState(appController, new GuiHelpMenu(ScreenFactory.getScreen())));

        //Play Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.PLAY);
        menuState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
        verify(appController, times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, ScreenFactory.getScreen()), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }
}
