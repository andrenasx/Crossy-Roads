package crossyroads.controller;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.states.GameState;
import crossyroads.controller.states.HelpState;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHelpMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class HelpStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = Mockito.mock(AppController.class);
        GuiHelpMenu gui = Mockito.mock(GuiHelpMenu.class);

        HelpState helpState = new HelpState(appController, gui);

        Mockito.when(gui.getNextCommand()).thenReturn(GuiHelpMenu.COMMAND.PLAY);

        helpState.step();

        GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
        TerminalScreen screen = ScreenFactory.getScreen();


        Mockito.verify(appController, Mockito.times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }
}
