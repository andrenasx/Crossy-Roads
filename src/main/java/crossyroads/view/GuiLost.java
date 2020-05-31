package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiLost implements Gui{
    private TerminalScreen screen;
    private int level;

    public GuiLost(TerminalScreen screen, int level){
        this.level = level;
        this.screen = screen;
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawLostMenu(graphics);
        drawLevel(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawLostMenu(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(7, 2, " __     __");
        graphics.putString(7, 3, " \\ \\   / /");
        graphics.putString(7, 4, "  \\ \\_/ /__  _   _ ");
        graphics.putString(7, 5, "   \\   / _ \\| | | |");
        graphics.putString(7, 6, "    | | (_) | |_| |");
        graphics.putString(7, 7, " _  |_|\\___/ \\__,_|    _");
        graphics.putString(7, 8, "| |             | |   | |");
        graphics.putString(7, 9, "| |     ___  ___| |_  | |");
        graphics.putString(7, 10, "| |    / _ \\/ __| __| | |");
        graphics.putString(7, 11, "| |___| (_) \\__ \\ |_  |_|");
        graphics.putString(7, 12, "|______\\___/|___/\\__| (_)");
    }

    private void drawButtons(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        int column = 5;
        for(int i = 0; i <= 2; i++){
            graphics.fillRectangle(
                    new TerminalPosition(column, 32),
                    new TerminalSize(10, 2),
                    ' ');
            column += 20;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(28,32,"EXIT");
        graphics.putString(28,33, "[ESC]");
        graphics.putString(8, 32, "MENU");
        graphics.putString(8, 33, "[1]");
    }

    private void drawLevel(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(11, 20, "Level reached: " + level);
    }

    public GuiLost.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getKeyType()){
            case Escape:
                return COMMAND.EXIT;
            case Character:
                if(input.getCharacter() == '1')
                return COMMAND.MENU;
            default:
                return COMMAND.NOTHING;
        }
    }
}
