package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.Chicken;
import crossyroads.model.GameModel;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiLost;
import crossyroads.view.GuiPauseMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameStateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiGame gui = mock(GuiGame.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);
        GameModel gameModel = mock(GameModel.class);
        ChickenController chickenController = mock(ChickenController.class);
        VehicleController vehicleController = mock(VehicleController.class);

        GameState gameState = new GameState(appController, gui, gameModel, chickenController, vehicleController);

        when(gameModel.isChickenDead()).thenReturn(false);
        when(gui.getNextCommand()).thenReturn(GuiGame.COMMAND.PAUSE);

        gameState.step();

        verify(appController, times(1)).setCurrentState(new PauseState(appController, new GuiPauseMenu(screen), gameState));


        when(gameModel.isChickenDead()).thenReturn(true);
        when(gameModel.getCurrentLevelInt()).thenReturn(1);
        when(gameModel.getScore()).thenReturn(1);
        Chicken chicken = mock(Chicken.class);
        when(chicken.getCountSteps()).thenReturn(1);
        when(gameModel.getChicken()).thenReturn(chicken);
        gameState.step();
        verify(appController, times(1)).setCurrentState(new LostState(appController, new GuiLost(screen, 1)));
    }
}
