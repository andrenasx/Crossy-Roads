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

import static com.googlecode.lanterna.input.KeyType.Escape;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiMainMenuTest {
    @Test
    public void commandsMenuTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiMainMenu gui = new GuiMainMenu(screen);

        //TODO : acrescentar o command NOTHING
        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiMainMenu.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiMainMenu.COMMAND.PLAY, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("2"));
        assertEquals(GuiMainMenu.COMMAND.HELP, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("3"));
        assertEquals(GuiMainMenu.COMMAND.HIGHSCORES, gui.getNextCommand());
    }

    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiMainMenu gui = new GuiMainMenu(screen);

        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(2)).enableModifiers(SGR.BOLD);

        //DrawButtons
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(18,15, "PLAY");
        verify(graphics,times(1)).putString(19,16, "[1]");
        verify(graphics,times(1)).putString(18,20, "HELP");
        verify(graphics,times(1)).putString(19,21, "[2]");
        verify(graphics,times(1)).putString(15,25, "HIGHSCORES");
        verify(graphics,times(1)).putString(19,26, "[3]");
        verify(graphics,times(1)).putString(18,30, "EXIT");
        verify(graphics,times(1)).putString(17,31, "[ESC]");

        //DrawMainMenu
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).putString(1, 1, "XXXX XXXXX  XXXX XXXXX XXXXX XXX  XXX");
        verify(graphics,times(1)).putString(1, 2, "X    XX  X  X  X XX    XX    XXX  XXX  ");
        verify(graphics,times(1)).putString(1, 3, "X    XXXX   X  X XXXXX XXXXX    XX  ");
        verify(graphics,times(1)).putString(1, 4, "X    XX  X  X  X    XX    XX    XX ");
        verify(graphics,times(1)).putString(1, 5, "XXXX XX   X XXXX XXXXX XXXXX    XX");
        verify(graphics,times(1)).putString(2, 8, "XXXXX   XXXXX     XXX     XXXX  XXXXX");
        verify(graphics,times(1)).putString(2, 9, "XX  X   X   X    X   X    X  X  XX");
        verify(graphics,times(1)).putString(2, 10, "XXXX    X   X   X XXX X   X  X  XXXXX");
        verify(graphics,times(1)).putString(2, 11, "XX  X   X   X  XX     XX  X  X     XX");
        verify(graphics,times(1)).putString(2, 12, "XX   X  XXXXX XX       XX XXX   XXXXX");

    }
}
