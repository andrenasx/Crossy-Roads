package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class HighscoreSateTest {
    AppController appController;
    GuiHighscoreMenu gui;
    TerminalScreen screen;
    HighscoreState highscoreState;

    @Before
    public void init(){
        appController = mock(AppController.class);
        gui = mock(GuiHighscoreMenu.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        highscoreState = new HighscoreState(appController, gui);
    }

    @Test
    public void stepPlayTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.PLAY);
        highscoreState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
        verify(appController,times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }

    @Test
    public void stepExitTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.EXIT);
        highscoreState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));
    }
}
