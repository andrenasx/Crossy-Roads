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
import static com.googlecode.lanterna.input.KeyType.Escape;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiMainMenuTest {
    @Test
    public void commandsMenuTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiMainMenu gui = new GuiMainMenu(screen);

        when(screen.readInput()).thenReturn(new KeyStroke(Enter));
        assertEquals(GuiMainMenu.COMMAND.NOTHING, gui.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiMainMenu.COMMAND.EXIT, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiMainMenu.COMMAND.PLAY, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("2"));
        assertEquals(GuiMainMenu.COMMAND.HELP, gui.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("3"));
        assertEquals(GuiMainMenu.COMMAND.HIGHSCORES, gui.getNextCommand());

        verify(screen, times((5))).readInput();
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
        verify(graphics,times(1)).putString(18,16,"[1]");
        verify(graphics,times(1)).putString(18,20, "HELP");
        verify(graphics,times(1)).putString(18,21, "[2]");
        verify(graphics,times(1)).putString(15,25, "HIGHSCORES");
        verify(graphics,times(1)).putString(18,26, "[3]");
        verify(graphics,times(1)).putString(18,30, "EXIT");
        verify(graphics,times(1)).putString(18,31, "[ESC]");

        //DrawMainMenu
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphics,times(1)).putString(4, 1, "  _____");
        verify(graphics,times(1)).putString(4, 2, " / ____|");
        verify(graphics,times(1)).putString(4, 3, "| |     _ __ ___  ___ ___ _   _ ");
        verify(graphics,times(1)).putString(4, 4, "| |    | '__/ _ \\/ __/ __| | | |");
        verify(graphics,times(1)).putString(4, 5, "| |____| | | (_) \\__ \\__ \\ |_| |");
        verify(graphics,times(1)).putString(4, 6, " \\_____|_|  \\___/|___/___/\\__, |");
        verify(graphics,times(1)).putString(4, 7, "|  __ \\               | |  __/ |");
        verify(graphics,times(1)).putString(4, 8, "| |__) |___   __ _  __| |_|___/ ");
        verify(graphics,times(1)).putString(4, 9, "|  _  // _ \\ / _` |/ _` / __|   ");
        verify(graphics,times(1)).putString(4, 10, "| | \\ \\ (_) | (_| | (_| \\__ \\");
        verify(graphics,times(1)).putString(4, 11, "|_|  \\_\\___/ \\__,_|\\__,_|___/");

    }
}
