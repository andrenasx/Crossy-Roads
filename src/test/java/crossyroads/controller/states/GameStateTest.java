package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiLost;
import crossyroads.view.GuiPauseMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiGame gui = mock(GuiGame.class);
        GameModel gameModel = mock(GameModel.class);
        ChickenController chickenController = mock(ChickenController.class);
        VehicleController vehicleController = mock(VehicleController.class);

        GameState gameState = new GameState(appController, gui, gameModel, chickenController, vehicleController);

        when(gameModel.isChickenDead()).thenReturn(false);
        when(gui.getNextCommand()).thenReturn(GuiGame.COMMAND.PAUSE);

        gameState.step();

        verify(appController, times(1)).setCurrentState(new PauseState(appController, new GuiPauseMenu(ScreenFactory.getScreen()), gameState));


        when(gameModel.isChickenDead()).thenReturn(true);
        gameState.step();
        verify(appController, times(1)).setCurrentState(new LostState(appController, new GuiLost(ScreenFactory.getScreen(), 1)));
    }
}
