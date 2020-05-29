package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GuiHighscoreMenuTest {
    @Test
    public void commandTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiHighscoreMenu guiHighscoreMenu = new GuiHighscoreMenu(screen);

        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        assertEquals(GuiHighscoreMenu.COMMAND.NOTHING, guiHighscoreMenu.getNextCommand());

        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
        assertEquals(GuiHighscoreMenu.COMMAND.BACK, guiHighscoreMenu.getNextCommand());

        when(screen.readInput()).thenReturn(KeyStroke.fromString("1"));
        assertEquals(GuiHighscoreMenu.COMMAND.PLAY, guiHighscoreMenu.getNextCommand());
    }

    @Test
    public void drawTest() throws IOException {
        TerminalScreen screen = mock(TerminalScreen.class);
        GuiHighscoreMenu guiHighscoreMenu = new GuiHighscoreMenu(screen);

        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        guiHighscoreMenu.draw();

        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#006600"));
        verify(graphics,times(2)).enableModifiers(SGR.BOLD);

        //drawHighscoreMenu
        verify(graphics,times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 36), ' ');
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphics,times(1)).putString(0, 3, " _  _ _      _");
        verify(graphics,times(1)).putString(0, 4, "| || (_)__ _| |_  ___ __ ___ _ _ ___ ___");
        verify(graphics,times(1)).putString(0, 5, "| __ | / _` | ' \\(_-</ _/ _ \\ '_/ -_|_-<");
        verify(graphics,times(1)).putString(0, 6, "|_||_|_\\__, |_||_/__/\\__\\___/_| \\___/__/");
        verify(graphics,times(1)).putString(0, 7, "       |___/");

        //drawButtons
        verify(graphics,times(1)).setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        verify(graphics,times(1)).setForegroundColor(TextColor.Factory.fromString("#25221e"));
        verify(graphics,times(1)).putString(28,32,"PLAY");
        verify(graphics,times(1)).putString(28,33,"[1]");
        verify(graphics,times(1)).putString(8, 32, "BACK");
        verify(graphics,times(1)).putString(8, 33, "[ESC]");

        //drawHighscores
        verify(graphics,times(1)).putString(5, 13, "Level   |   Coins   |   Steps");
    }
}
