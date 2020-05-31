package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.view.Gui;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiPauseMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PauseStateTest {
    AppController appController;
    GuiPauseMenu gui;
    TerminalScreen screen;
    GameState gameState;

    PauseState pauseState;

    @Before
    public void init(){
        appController = mock(AppController.class);
        gui = mock(GuiPauseMenu.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);
        gameState = mock(GameState.class);

        pauseState = new PauseState(appController, gui, gameState);
    }

    @Test
    public void stepResumeTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.PLAY);
        pauseState.step();
        verify(appController,times(1)).setCurrentState(gameState);
    }

    @Test
    public void stepMenuTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.MENU);
        pauseState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));
    }
}
