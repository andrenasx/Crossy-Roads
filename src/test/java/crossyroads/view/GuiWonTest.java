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

        //TODO : acrescentar o command NOTHING

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiWon.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiWon.COMMAND.MENU, gui.getNextCommand());

        verify(screen, times((2))).readInput();
    }

    @Test
    public void drawStatsTest() throws IOException {
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
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');

        verify(graphics,times(1)).putString(3, 20, "Score: " + score + "\tHealth: " + health + "\tSteps: " + steps);
    }
}
