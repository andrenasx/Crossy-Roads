package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHighscoreMenu;
import crossyroads.view.GuiMainMenu;
import crossyroads.view.ScreenFactory;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class HighscoreSateTest {
    @Test
    public void stepTest() throws IOException {
        AppController appController = mock(AppController.class);
        GuiHighscoreMenu guiHighscoreMenu = mock(GuiHighscoreMenu.class);

        HighscoreState highscoreState = new HighscoreState(appController, guiHighscoreMenu);

        //Play command
        when(guiHighscoreMenu.getNextCommand()).thenReturn(GuiHighscoreMenu.COMMAND.PLAY);
        highscoreState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35, 5);
        verify(appController,times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, ScreenFactory.getScreen()), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));

        //Back command
        when(guiHighscoreMenu.getNextCommand()).thenReturn(GuiHighscoreMenu.COMMAND.BACK);
        highscoreState.step();
        verify(appController,times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(ScreenFactory.getScreen())));

    }
}
