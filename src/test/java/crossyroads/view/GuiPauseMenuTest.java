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

import static com.googlecode.lanterna.input.KeyType.Enter;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiPauseMenuTest {
    @Test
    public void commandPauseTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiPauseMenu gui = new GuiPauseMenu(screen);

        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiPauseMenu.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiPauseMenu.COMMAND.MENU, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("2"));
        assertEquals(GuiPauseMenu.COMMAND.RESUME, gui.getNextCommand());
    }

    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiPauseMenu gui = new GuiPauseMenu(screen);
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        gui.draw();

        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(2)).enableModifiers(SGR.BOLD);

        //DrawButtons
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(27,25,"RESUME");
        verify(graphics,times(1)).putString(28,26, "[2]");
        verify(graphics,times(1)).putString(8, 25, "MENU");
        verify(graphics,times(1)).putString(8, 26, "[1]");

        //DrawPauseMenu
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphics,times(1)).putString(6, 5, " _____");
        verify(graphics,times(1)).putString(6, 6, "|  __ \\");
        verify(graphics,times(1)).putString(6, 7, "| |__) |_ _ _   _ ___  ___");
        verify(graphics,times(1)).putString(6, 8, "|  ___/ _` | | | / __|/ _ \\");
        verify(graphics,times(1)).putString(6, 9, "| |  | (_| | |_| \\__ \\  __/");
        verify(graphics,times(1)).putString(6, 10, "|_|   \\__,_|\\__,_|___/\\___");

    }
}
