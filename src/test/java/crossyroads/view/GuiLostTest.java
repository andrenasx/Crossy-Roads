package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiLostTest {
    @Test
    public void commandLostTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        Random rand = new Random();
        int level = rand.nextInt(10);
        GuiLost gui = new GuiLost(screen, level);

        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiLost.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiLost.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiLost.COMMAND.MENU, gui.getNextCommand());

        verify(screen, times((3))).readInput();
    }

    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        Random rand = new Random();
        int level = rand.nextInt(10);
        GuiLost gui = new GuiLost(screen, level);

        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(2)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(3)).enableModifiers(SGR.BOLD);

        //DrawButtons and level
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(27,32, "EXIT");
        verify(graphics,times(1)).putString(27,33, "[ESC]");
        verify(graphics,times(1)).putString(7, 32, "MENU");
        verify(graphics,times(1)).putString(8, 33, "[1]");
        verify(graphics,times(1)).putString(11,20, "Level reached: " + level);

        //DrawLostMenu

        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).putString(5, 2, "XXX  XXX  XXXXXX  XX   XX  ");
        verify(graphics,times(1)).putString(5, 3, "XXX  XXX  XX  XX  XX   XX");
        verify(graphics,times(1)).putString(5, 4, "   XX     XX  XX  XX   XX");
        verify(graphics,times(1)).putString(5, 5, "   XX     XX  XX  XX   XX");
        verify(graphics,times(1)).putString(5, 6, "   XX     XXXXXX  XXXXXXX");
        verify(graphics,times(1)).putString(4, 9, "XX     XXXXXX  XXXXX  XXXXXXX  XXX");
        verify(graphics,times(1)).putString(4, 10, "XX     XX  XX  XX        XX    XXX");
        verify(graphics,times(1)).putString(4, 11, "XX     XX  XX  XXXXX     XX    XXX");
        verify(graphics,times(1)).putString(4, 12, "XX     XX  XX     XX     XX         ");
        verify(graphics,times(1)).putString(4, 13, "XXXXX  XXXXXX  XXXXX     XX    XXX");
    }
}
