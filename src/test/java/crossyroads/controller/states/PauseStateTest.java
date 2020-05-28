package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.GuiPauseMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PauseStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiPauseMenu guiPauseMenu = mock(GuiPauseMenu.class);
        GameState gameState = mock(GameState.class);

        PauseState pauseState = new PauseState(appController, guiPauseMenu, gameState);

        //Resume command
        when(guiPauseMenu.getNextCommand()).thenReturn(GuiPauseMenu.COMMAND.RESUME);
        pauseState.step();
        verify(appController,times(1)).setCurrentState(gameState);

        //Menu command
        when(guiPauseMenu.getNextCommand()).thenReturn(GuiPauseMenu.COMMAND.MENU);
        pauseState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));

    }
}
