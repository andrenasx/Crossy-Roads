package crossyroads.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.Escape;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiHelpMenuTest {
    @Test
    public void commandTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiHelpMenu gui = new GuiHelpMenu(screen);

        //TODO : acrescentar o command NOTHING
        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiHelpMenu.COMMAND.BACK, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiHelpMenu.COMMAND.PLAY, gui.getNextCommand());

        verify(screen, times((2))).readInput();
    }
}
