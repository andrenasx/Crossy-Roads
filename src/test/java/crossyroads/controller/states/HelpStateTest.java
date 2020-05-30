package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
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

import static org.mockito.Mockito.*;

import java.io.IOException;

public class HelpStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiHelpMenu gui = mock(GuiHelpMenu.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        HelpState helpState = new HelpState(appController, gui);

        //Back Command
        when(gui.getNextCommand()).thenReturn(GuiHelpMenu.COMMAND.BACK);
        helpState.step();
        verify(appController, times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));

        //Play Command
        when(gui.getNextCommand()).thenReturn(GuiHelpMenu.COMMAND.PLAY);
        helpState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
        verify(appController, times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }
}
