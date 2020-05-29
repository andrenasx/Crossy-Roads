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

    public GuiHelpMenu(TerminalScreen screen){
        this.screen = screen;
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawHelpMenu(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawHelpMenu(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(9, 2, " _    _      _");
        graphics.putString(9, 3, "| |  | |    | |");
        graphics.putString(9, 4, "| |__| | ___| |_ __");
        graphics.putString(9, 5, "|  __  |/ _ \\ | '_ \\");
        graphics.putString(9, 6, "| |  | |  __/ | |_) |");
        graphics.putString(9, 7, "|_|  |_|\\___|_| .__/");
        graphics.putString(9, 8, "              | |");
        graphics.putString(9, 9, "              |_|");

        List<String> instructions = new ArrayList<>();
        String inst1 = "-To move use the keyboard arrows";
        instructions.add(inst1);
        String inst2 = "-To pause the game click ESCAPE";
        instructions.add(inst2);
        String inst3 = "-Collect all the coins";
        instructions.add(inst3);
        String inst4 = "-Reach the finish line on the top";
        instructions.add(inst4);
        String inst5 = "-You lose health when hitting a vehicle";
        instructions.add(inst5);
        String inst6 = "------------>Have fun!!!!!!<------------";
        instructions.add(inst6);
        int row = 13;
        for (String instruction : instructions) {
            graphics.putString(0, row, instruction);
            row += 2;
        }

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
        graphics.putString(28,32,"PLAY");
        graphics.putString(28,33,"[1]");
        graphics.putString(8, 32, "BACK");
        graphics.putString(8, 33, "[ESC]");

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
