package crossyroads.view;

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
}
