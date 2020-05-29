package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.Chicken;
import crossyroads.model.GameModel;
import crossyroads.view.*;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameStateTest {
    @Test
    public void stepPauseTest() throws IOException {
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
    }

    @Test
    public void stepLostTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiGame gui = mock(GuiGame.class);
        GameModel gameModel = mock(GameModel.class);
        ChickenController chickenController = mock(ChickenController.class);
        VehicleController vehicleController = mock(VehicleController.class);

        GameState gameState = new GameState(appController, gui, gameModel, chickenController, vehicleController);

        when(gameModel.isChickenDead()).thenReturn(true);
        when(gameModel.getCurrentLevelInt()).thenReturn(1);
        when(gameModel.getScore()).thenReturn(1);
        Chicken chicken = mock(Chicken.class);
        when(chicken.getCountSteps()).thenReturn(1);
        when(gameModel.getChicken()).thenReturn(chicken);
        gameState.step();
        verify(appController, times(1)).setCurrentState(new LostState(appController, new GuiLost(ScreenFactory.getScreen(), 1)));
    }

    @Test
    public void stepWonTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiGame gui = mock(GuiGame.class);
        GameModel gameModel = mock(GameModel.class);
        ChickenController chickenController = mock(ChickenController.class);
        VehicleController vehicleController = mock(VehicleController.class);

        GameState gameState = new GameState(appController, gui, gameModel, chickenController, vehicleController);

        when(gameModel.isChickenDead()).thenReturn(false);
        when(gui.getNextCommand()).thenReturn(GuiGame.COMMAND.DOWN);
        when(gameModel.isLevelFinished()).thenReturn(true);
        when(gameModel.isFinalLevel()).thenReturn(true);
        when(gameModel.getScore()).thenReturn(35);
        when(gameModel.getLives()).thenReturn(2);
        Chicken chicken = mock(Chicken.class);
        when(gameModel.getChicken()).thenReturn(chicken);
        when(chicken.getCountSteps()).thenReturn(200);
        gameState.step();
        verify(chickenController,times(1)).start(GuiGame.COMMAND.DOWN);
        verify(vehicleController,times(1)).start(1);

        verify(appController).setCurrentState(new WonState(appController, new GuiWon(gameModel.getScore(), gameModel.getLives(), gameModel.getChicken().getCountSteps(), ScreenFactory.getScreen())));
    }
}
