package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiPauseMenu implements Gui{
    private TerminalScreen screen;

    public GuiPauseMenu(TerminalScreen screen){
        this.screen = screen;
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawPauseMenu(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawPauseMenu(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(6, 5, " _____");
        graphics.putString(6, 6, "|  __ \\");
        graphics.putString(6, 7, "| |__) |_ _ _   _ ___  ___");
        graphics.putString(6, 8, "|  ___/ _` | | | / __|/ _ \\");
        graphics.putString(6, 9, "| |  | (_| | |_| \\__ \\  __/");
        graphics.putString(6, 10, "|_|   \\__,_|\\__,_|___/\\___");
    }

    private void drawButtons(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        int column = 5;
        for(int i = 0; i <= 2; i++){
            graphics.fillRectangle(
                    new TerminalPosition(column, 25),
                    new TerminalSize(10, 2),
                    ' ');
            column += 20;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(7, 25, "RESUME");
        graphics.putString(7, 26, "[ESC]");
        graphics.putString(28,25,"MENU");
        graphics.putString(28,26, "[1]");

    }

    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getKeyType()){
            case Escape:
                return COMMAND.PLAY;
            case Character:
                if(input.getCharacter() == '1')
                    return COMMAND.MENU;
            default:
                return COMMAND.NOTHING;
        }
    }
}
