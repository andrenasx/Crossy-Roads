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

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiHelpMenuTest {
    @Test
    public void commandTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiHelpMenu gui = new GuiHelpMenu(screen);

        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiHelpMenu.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiHelpMenu.COMMAND.BACK, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiHelpMenu.COMMAND.PLAY, gui.getNextCommand());

        verify(screen, times((3))).readInput();
    }

    @Test
    public void draw() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiHelpMenu gui = new GuiHelpMenu(screen);

        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(2)).enableModifiers(SGR.BOLD);

        //drawHelpMenu
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).putString(5, 2, "XX   XX  XXXXXX  XXX     XXXXX");
        verify(graphics,times(1)).putString(5, 3, "XX   XX  XXX     XXX     XX   X");
        verify(graphics,times(1)).putString(5, 4, "XXXXXXX  XXXXX   XXX     XXXXX");
        verify(graphics,times(1)).putString(5, 5, "XX   XX  XXX     XXX     XX");
        verify(graphics,times(1)).putString(5, 6, "XX   XX  XXXXXX  XXXXXX  XX");

        //drawButtons
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(26,32,"PLAY");
        verify(graphics,times(1)).putString(27,33,"[1]");
        verify(graphics,times(1)).putString(7, 32, "BACK");
        verify(graphics,times(1)).putString(7, 33, "[ESC]");
    }
}
