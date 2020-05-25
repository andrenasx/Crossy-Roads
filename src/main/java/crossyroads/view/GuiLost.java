package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiLost {
    private TerminalScreen screen;
    private int level;
    public enum COMMAND {MENU, EXIT, NOTHING}

    public GuiLost(int level) throws IOException {
        this.level = level;
        this.screen = ScreenFactory.getScreen();
    }

    public void draw() throws IOException {
        screen.clear();
        drawLostMenu();
        drawLevel();
        drawButtons();
        screen.refresh();
    }

    private void drawLostMenu(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.putString(5, 2, "YYY  YYY  OOOOOO  UU   UU  ");
        graphics.putString(5, 3, "   YY     OO  OO  UU   UU");
        graphics.putString(5, 4, "   YY     OO  OO  UU   UU");
        graphics.putString(5, 5, "   YY     OOOOOO  UUUUUUU");
        graphics.putString(4, 8, "LL     OOOOOO  SSSSS  TTTTTTTT  |||");
        graphics.putString(4, 9, "LL     OO  OO  SS        TT     |||");
        graphics.putString(4, 10, "LL     OO  OO  SSSSS     TT     |||");
        graphics.putString(4, 11, "LL     OO  OO     SS     TT         ");
        graphics.putString(4, 12, "LLLLL  OOOOOO  SSSSS     TT     ...");
    }

    private void drawButtons(){
        TextGraphics graphics = screen.newTextGraphics();
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
        graphics.putString(26,32,"EXIT");
        graphics.putString(27,33, "[1]");
        graphics.putString(7, 32, "MENU");
        graphics.putString(8, 33, "[2]");

    }

    private void drawLevel(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(11, 20, "Level reached: " + level);
    }

    public GuiLost.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getCharacter()){
            case '1':
                return COMMAND.EXIT;
            case '2':
                return COMMAND.MENU;
            default:
                return COMMAND.NOTHING;
        }
    }
}
