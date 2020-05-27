package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GuiGameTest {
    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        GameModel gameModel = Mockito.mock(GameModel.class);
        GuiGame gui = new GuiGame(gameModel, screen);
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        Chicken chicken = new Chicken(10, 10);
        when(gameModel.getChicken()).thenReturn(chicken);
        when(gameModel.getScore()).thenReturn(0);
        when(gameModel.getLives()).thenReturn(3);
        when(gameModel.getCurrentLevelInt()).thenReturn(1);
        when(gameModel.getHeight()).thenReturn(35);

        Level level = mock(Level.class);
        when(gameModel.getCurrentLevel()).thenReturn(level);
        when(level.getLevelBackground()).thenReturn("dgggggggggrrrggggggggggrrrggggggggg");

        List<Element> elements = new ArrayList<>();
        elements.add(new Coin(15,20,1));
        elements.add(new Car(10,10,"left"));
        elements.add(new Truck(10,5,"right"));
        when(level.getAllElements()).thenReturn(elements);

        gui.draw();

        verify(graphics,times(5)).enableModifiers(SGR.BOLD);
        verify(screen.newTextGraphics(),times(1)).putString(0,35, "Score: 0\tHealth: 3\tLevel: 1");
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphics,times(30)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(screen.newTextGraphics(),times(1)).putString(10,10, "O");
        verify(graphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphics, times(30)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(screen.newTextGraphics(),times(1)).putString(15,20, "O");
        verify(graphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(graphics,times(8)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(screen.newTextGraphics(),times(1)).putString(10,10, "<>");
        verify(graphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        verify(screen.newTextGraphics(),times(1)).putString(10,5, "<==>");
    }

}
