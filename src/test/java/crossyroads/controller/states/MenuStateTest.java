package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.*;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MenuStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiMainMenu gui = mock(GuiMainMenu.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        MenuState menuState = new MenuState(appController, gui);

        //Exit Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.EXIT);
        menuState.step();
        verify(appController, times(1)).setEnd();

        //HighScore Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.HIGHSCORES);
        menuState.step();
        verify(appController, times(1)).setCurrentState(new HighscoreState(appController, new GuiHighscoreMenu(screen)));

        //Help Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.HELP);
        menuState.step();
        verify(appController, times(1)).setCurrentState(new HelpState(appController, new GuiHelpMenu(screen)));

        //Play Command
        when(gui.getNextCommand()).thenReturn(GuiMainMenu.COMMAND.PLAY);
        menuState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
        verify(appController, times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }
}
