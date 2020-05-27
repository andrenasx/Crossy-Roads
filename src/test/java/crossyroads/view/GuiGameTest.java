package crossyroads.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.model.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GuiGameTest {
    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        GameModel gameModel = Mockito.mock(GameModel.class);
        GuiGame gui = new GuiGame(gameModel, screen);
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        /*List<Element> elements = new ArrayList<>();
        elements.add(new Chicken(20,20));
        elements.add(new Coin(15,20,1));
        elements.add(new Car(10,10,"left"));
        elements.add(new Truck(10,5,"right"));
        when(level.getAllElements()).thenReturn(elements);*/
        /*Chicken chicken = new Chicken(10, 10);
        when(gameModel.getChicken()).thenReturn(chicken);

        gui.draw();

        verify(screen.newTextGraphics(),times(1)).putString(0,35, "Score: 10\tHealth: 3\tLevel: 1");
        //verify(screen.newTextGraphics(),times(1)).putString(20,20, "O");
        //verify(screen.newTextGraphics(),times(1)).putString(15,20, "O");
        //verify(screen.newTextGraphics(),times(1)).putString(10,10, "<>");
        //verify(screen.newTextGraphics(),times(1)).putString(10,5, "<==>");*/
    }

}
