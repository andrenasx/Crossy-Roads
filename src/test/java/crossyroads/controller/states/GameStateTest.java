package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.Chicken;
import crossyroads.model.GameModel;
import crossyroads.view.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameStateTest {
    AppController appController;
    GuiGame gui;
    TerminalScreen screen;
    GameModel gameModel;
    ChickenController chickenController;
    VehicleController vehicleController;
    GameState gameState;

    @Before
    public void init(){
        appController = mock(AppController.class);
        gui = mock(GuiGame.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);
        gameModel = mock(GameModel.class);
        chickenController = mock(ChickenController.class);
        vehicleController = mock(VehicleController.class);

        gameState = new GameState(appController, gui, gameModel, chickenController, vehicleController);
    }

    @Test
    public void stepPauseTest() throws IOException {
        when(gameModel.isChickenDead()).thenReturn(false);
        when(gui.getNextCommand()).thenReturn(GuiGame.COMMAND.PAUSE);

        gameState.step();

        verify(appController, times(1)).setCurrentState(new PauseState(appController, new GuiPauseMenu(screen), gameState));
    }

    @Test
    public void stepLostTest() throws IOException {
        when(gameModel.isChickenDead()).thenReturn(true);
        when(gameModel.getCurrentLevelInt()).thenReturn(1);
        when(gameModel.getScore()).thenReturn(1);
        Chicken chicken = mock(Chicken.class);
        when(chicken.getCountSteps()).thenReturn(1);
        when(gameModel.getChicken()).thenReturn(chicken);
        gameState.step();
        verify(appController, times(1)).setCurrentState(new LostState(appController, new GuiLost(screen, 1)));
    }

    @Test
    public void stepWonTest() throws IOException {
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

        verify(appController).setCurrentState(new WonState(appController, new GuiWon(gameModel.getScore(), gameModel.getLives(), gameModel.getChicken().getCountSteps(), screen)));
    }
}
