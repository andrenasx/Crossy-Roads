package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiHelpMenuTest {
    TerminalScreen screen;
    GuiHelpMenu gui;

    @Before
    public void init() {
        screen = mock(TerminalScreen.class);
        gui = new GuiHelpMenu(screen);
    }

    @Test
    public void getScreen() {
        assertEquals(screen, gui.getScreen());
    }

    @Test
    public void commandTest() throws IOException {
        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiHelpMenu.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiHelpMenu.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiHelpMenu.COMMAND.PLAY, gui.getNextCommand());

        verify(screen, times((3))).readInput();
    }

    @Test
    public void draw() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(2)).enableModifiers(SGR.BOLD);

        //drawHelpMenu
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).putString(9, 2, " _    _      _");
        verify(graphics,times(1)).putString(9, 3, "| |  | |    | |");
        verify(graphics,times(1)).putString(9, 4, "| |__| | ___| |_ __");
        verify(graphics,times(1)).putString(9, 5, "|  __  |/ _ \\ | '_ \\");
        verify(graphics,times(1)).putString(9, 6, "| |  | |  __/ | |_) |");
        verify(graphics,times(1)).putString(9, 7, "|_|  |_|\\___|_| .__/");
        verify(graphics,times(1)).putString(9, 8, "              | |");
        verify(graphics,times(1)).putString(9, 9, "              |_|");

        //drawButtons
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(28,32,"PLAY");
        verify(graphics,times(1)).putString(28,33,"[1]");
        verify(graphics,times(1)).putString(8, 32, "BACK");
        verify(graphics,times(1)).putString(8, 33, "[ESC]");
    }
}
