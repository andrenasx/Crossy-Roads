package crossyroads.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ViewTest {
    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        when(screen.newTextGraphics()).thenReturn(mock(TextGraphics.class));
        GameModel gameModel = Mockito.mock(GameModel.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        when(gameMap.getWidth()).thenReturn(40);
        when(gameMap.getHeight()).thenReturn(35);
        when(gameMap.getLives()).thenReturn(3);
        when(gameMap.getScore()).thenReturn(10);
        when(gameMap.getLevelBackground()).thenReturn("dgggggggggrrrggggggggggrrrggggggggg");
        Gui gui = new Gui(gameModel);
        gui.setScreen(screen);

        List<Element> elements = new ArrayList<>();
        elements.add(new Chicken(20,20));
        elements.add(new Coin(15,20,1));
        elements.add(new Car(10,10,"left"));
        elements.add(new Truck(10,5,"right"));
        when(gameMap.getAllElements()).thenReturn(elements);

        //gui.draw();

        /*verify(screen, times(6)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).putString(0,35, "Score: 10\tHealth: 3");
        verify(screen.newTextGraphics(),times(1)).putString(20,20, "O");
        verify(screen.newTextGraphics(),times(1)).putString(15,20, "O");
        verify(screen.newTextGraphics(),times(1)).putString(10,10, "<>");
        verify(screen.newTextGraphics(),times(1)).putString(10,5, "<==>");*/
    }

    @Test
    public void commandTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        GameModel gameModel = Mockito.mock(GameModel.class);
        Gui gui = new Gui(gameModel);
        gui.setScreen(screen);


        assertEquals(Gui.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(Gui.COMMAND.UP, gui.getNextCommand());

        when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(Gui.COMMAND.DOWN, gui.getNextCommand());

        when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(Gui.COMMAND.LEFT, gui.getNextCommand());

        when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(Gui.COMMAND.RIGHT, gui.getNextCommand());

        verify(screen, times((5))).pollInput();
    }

}
