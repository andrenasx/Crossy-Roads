package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHighscoreMenu;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class HighscoreSateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiHighscoreMenu gui = mock(GuiHighscoreMenu.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        HighscoreState highscoreState = new HighscoreState(appController, gui);

        //Play command
        when(gui.getNextCommand()).thenReturn(GuiHighscoreMenu.COMMAND.PLAY);
        highscoreState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
        verify(appController,times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));

        //Back command
        when(gui.getNextCommand()).thenReturn(GuiHighscoreMenu.COMMAND.BACK);
        highscoreState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));

    }
}
