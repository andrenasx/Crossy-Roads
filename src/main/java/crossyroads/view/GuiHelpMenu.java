package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiHelpMenu {
    private TerminalScreen screen;
    public enum COMMAND {PLAY, BACK, NOTHING}

    public GuiHelpMenu() throws IOException {
        this.screen = ScreenFactory.getScreen();
    }

    public void draw() throws IOException {
        screen.clear();
        drawHelpMenu();
        drawButtons();
        screen.refresh();
    }

    private void drawHelpMenu(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.putString(5, 2, "XX   XX  XXXXXX  XXX     XXXXX");
        graphics.putString(5, 3, "XX   XX  XXX     XXX     XX   X");
        graphics.putString(5, 4, "XXXXXXX  XXXXX   XXX     XXXXX");
        graphics.putString(5, 5, "XX   XX  XXX     XXX     XX");
        graphics.putString(5, 6, "XX   XX  XXXXXX  XXXXXX  XX");
        List<String> instructions = new ArrayList<>();
        String inst1 = "-To move use the arrow of the keyboard";
        instructions.add(inst1);
        String inst2 = "-To exit the game click ESCAPE";
        instructions.add(inst2);
        String inst3 = "-Collect all the coins";
        instructions.add(inst3);
        String inst4 = "-Reach the finish line on the top";
        instructions.add(inst4);
        String inst5 = "-You lose health when hitting a vehicle";
        instructions.add(inst5);
        String inst6 = "------------>Have fun!!!!!!<------------";
        instructions.add(inst6);
        int row = 11;
        for (String instruction : instructions) {
            graphics.putString(0, row, instruction);
            row += 2;
        }

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
        graphics.putString(26,32,"PLAY");
        graphics.putString(27,33,"[1]");
        graphics.putString(7, 32, "BACK");
        graphics.putString(7, 33, "[ESC]");

    }

    public GuiHelpMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getKeyType()){
            case Escape:
                return COMMAND.BACK;
            case Character:
                if(input.getCharacter() == '1')
                return COMMAND.PLAY;
            default:
                return COMMAND.NOTHING;
        }
    }
}
