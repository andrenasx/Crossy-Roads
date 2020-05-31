package crossyroads.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.view.Gui;
import crossyroads.view.GuiGame;
import crossyroads.view.GuiHelpMenu;
import crossyroads.view.GuiMainMenu;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;

public class HelpStateTest {
    AppController appController;
    GuiHelpMenu gui;
    TerminalScreen screen;
    HelpState helpState;

    @Before
    public void init(){
        appController = mock(AppController.class);
        gui = mock(GuiHelpMenu.class);
        screen = mock(TerminalScreen.class);
        when(gui.getScreen()).thenReturn(screen);

        helpState = new HelpState(appController, gui);
    }

    @Test
    public void stepExitTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.EXIT);
        helpState.step();
        verify(appController, times(1)).setCurrentState(new MenuState(appController, new GuiMainMenu(screen)));
    }

    @Test
    public void stepPlayTest() throws IOException {
        when(gui.getNextCommand()).thenReturn(Gui.COMMAND.PLAY);
        helpState.step();
        GameModel gameModel = new GameModelCreator().createGameModel(40, 35);
        verify(appController, times(1)).setCurrentState(new GameState(appController, new GuiGame(gameModel, screen), gameModel, new ChickenController(gameModel), new VehicleController(gameModel)));
    }
}
