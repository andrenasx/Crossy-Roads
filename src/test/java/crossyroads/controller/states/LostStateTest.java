package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiLost;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;

public class LostStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiLost gui = mock(GuiLost.class);

        LostState lostState = new LostState(appController, gui);

        //Exit Command
        when(gui.getNextCommand()).thenReturn(GuiLost.COMMAND.EXIT);
        lostState.step();
        verify(appController, times(1)).setEnd();

        //Menu Command
        when(gui.getNextCommand()).thenReturn(GuiLost.COMMAND.MENU);
        lostState.step();
        verify(appController, times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));
    }
}
