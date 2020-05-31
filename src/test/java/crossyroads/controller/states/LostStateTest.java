package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.view.Gui;
import crossyroads.view.GuiLost;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;

public class LostStateTest {
    AppController appController;
    GuiLost gui;
    TerminalScreen screen;
    LostState lostState;

    @Before
    public void init() {
        appController = mock(AppController.class);
        gui = mock(GuiLost.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        lostState = new LostState(appController, gui);
    }

    @Test
    public void stepMenuTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.MENU);
        lostState.step();
        verify(appController, times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));
    }

    @Test
    public void stepExitTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.EXIT);
        lostState.step();
        verify(appController, times(1)).setEnd();
    }
}
