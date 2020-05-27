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

import static com.googlecode.lanterna.input.KeyType.Enter;
import static com.googlecode.lanterna.input.KeyType.Escape;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GuiWonTest {
    @Test
    public void commandWonTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        Random rand = new Random();
        int score = rand.nextInt(30);
        int health = rand.nextInt(3);
        int steps = rand.nextInt(50);
        GuiWon gui = new GuiWon(score, health, steps, screen);

        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiWon.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiWon.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiWon.COMMAND.MENU, gui.getNextCommand());

        verify(screen, times((3))).readInput();
    }

    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        Random rand = new Random();
        int score = rand.nextInt(30);
        int health = rand.nextInt(3);
        int steps = rand.nextInt(50);
        GuiWon gui = new GuiWon(score, health, steps, screen);

        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(2)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(3)).enableModifiers(SGR.BOLD);

        //drawWonMenu
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphics,times(1)).putString(7, 2, " __     __");
        verify(graphics,times(1)).putString(7, 3, " \\ \\   / /");
        verify(graphics,times(1)).putString(7, 4, "  \\ \\_/ /__  _   _ ");
        verify(graphics,times(1)).putString(7, 5, "   \\   / _ \\| | | |");
        verify(graphics,times(1)).putString(7, 6, "    | | (_) | |_| |");
        verify(graphics,times(1)).putString(7, 7, "__  |_|\\___/_\\__,_|      _");
        verify(graphics,times(1)).putString(7, 8, "\\ \\        / /          | |");
        verify(graphics,times(1)).putString(7, 9, " \\ \\  /\\  / /__  _ __   | |");
        verify(graphics,times(1)).putString(7, 10, "  \\ \\/  \\/ / _ \\| '_ \\  | |");
        verify(graphics,times(1)).putString(7, 11, "   \\  /\\  / (_) | | | | |_|");
        verify(graphics,times(1)).putString(7, 12, "    \\/  \\/ \\___/|_| |_| (_)");

        //drawButton
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(28,32,"EXIT");
        verify(graphics,times(1)).putString(8, 32, "MENU");
        verify(graphics,times(1)).putString(8, 33, "[1]");

        verify(graphics,times(1)).putString(3, 20, "Score: " + score + "\tHealth: " + health + "\tSteps: " + steps);
    }
}
