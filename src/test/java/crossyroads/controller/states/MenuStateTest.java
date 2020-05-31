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

public class MenuStateTest {
    AppController appController;
    GuiMainMenu gui;
    TerminalScreen screen;
    MenuState menuState;

    @Before
    public void init(){
        appController = mock(AppController.class);
        gui = mock(GuiMainMenu.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        menuState = new MenuState(appController, gui);
    }

    @Test
    public void stepPlayTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.PLAY);
        menuState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
        verify(appController, times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }

    @Test
    public void stepHelpTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.HELP);
        menuState.step();
        verify(appController, times(1)).setCurrentState(new HelpState(appController, new GuiHelpMenu(screen)));
    }

    @Test
    public void stepScoreTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.HIGHSCORES);
        menuState.step();
        verify(appController, times(1)).setCurrentState(new HighscoreState(appController, new GuiHighscoreMenu(screen)));
    }

    @Test
    public void stepExitTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.EXIT);
        menuState.step();
        verify(appController, times(1)).setEnd();
    }
}
