package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiWon;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class WonStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiWon guiWon = mock(GuiWon.class);

        WonState wonState = new WonState(appController, guiWon);

        //Exit command
        when(guiWon.getNextCommand()).thenReturn(GuiWon.COMMAND.EXIT);
        wonState.step();
        verify(appController,times(1)).setEnd();

        //Menu command
        when(guiWon.getNextCommand()).thenReturn(GuiWon.COMMAND.MENU);
        wonState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));

    }
}
