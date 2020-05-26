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
        graphics.putString(5, 2, "XXX  XXX  XXXXXX  XX   XX  ");
        graphics.putString(5, 3, "XXX  XXX  XX  XX  XX   XX");
        graphics.putString(5, 4, "   XX     XX  XX  XX   XX");
        graphics.putString(5, 5, "   XX     XX  XX  XX   XX");
        graphics.putString(5, 6, "   XX     XXXXXX  XXXXXXX");
        graphics.putString(4, 9, "XX     XXXXXX  XXXXX  XXXXXXX  XXX");
        graphics.putString(4, 10, "XX     XX  XX  XX        XX    XXX");
        graphics.putString(4, 11, "XX     XX  XX  XXXXX     XX    XXX");
        graphics.putString(4, 12, "XX     XX  XX     XX     XX         ");
        graphics.putString(4, 13, "XXXXX  XXXXXX  XXXXX     XX    XXX");
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
        graphics.putString(27,32,"EXIT");
        graphics.putString(27,33, "[ESC]");
        graphics.putString(7, 32, "MENU");
        graphics.putString(8, 33, "[1]");

    }

    private void drawLevel(){
        TextGraphics graphics = screen.newTextGraphics();
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
