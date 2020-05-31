package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.view.Gui;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiWon;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class WonStateTest {
    AppController appController;
    GuiWon gui;
    TerminalScreen screen;
    WonState wonState;

    @Before
    public void init() {
        appController = mock(AppController.class);
        gui = mock(GuiWon.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        wonState = new WonState(appController, gui);
    }

    @Test
    public void stepMenuTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.MENU);
        wonState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));
    }

    @Test
    public void stepExitTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.EXIT);
        wonState.step();
        verify(appController,times(1)).setEnd();
    }
}
