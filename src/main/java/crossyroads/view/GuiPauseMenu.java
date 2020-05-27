package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiPauseMenu {
    private TerminalScreen screen;
    public enum COMMAND {RESUME, MENU, NOTHING}

    public GuiPauseMenu(TerminalScreen screen){
        this.screen = screen;
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
        graphics.setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.putString(3, 2, "XXXXX    XX     X   X XXXXX XXXXX");
        graphics.putString(3, 3, "X    X  X  X    X   X XX    XX");
        graphics.putString(3, 4, "XXXXX  X XX X   X   X XXXXX XXXX");
        graphics.putString(3, 5, "X     X      X  X   X    XX XX");
        graphics.putString(3, 6, "X    X        X XXXXX XXXXX XXXXX");
    }

    private void drawButtons(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        int column = 5;
        for(int i = 0; i <= 2; i++){
            graphics.fillRectangle(
                    new TerminalPosition(column, 32),
                    new TerminalSize(9, 2),
                    ' ');
            column += 20;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(25,32,"RESUME");
        graphics.putString(27,33, "[2]");
        graphics.putString(7, 32, "MENU");
        graphics.putString(8, 33, "[1]");
    }

    public GuiPauseMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getCharacter()){
            case '1':
                return COMMAND.MENU;
            case '2':
                return COMMAND.RESUME;
            default:
                return COMMAND.NOTHING;
        }
    }
}
