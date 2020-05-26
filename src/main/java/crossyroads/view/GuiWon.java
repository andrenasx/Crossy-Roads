package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiWon {
    private TerminalScreen screen;
    private int score, health, steps;
    public enum COMMAND {MENU, EXIT, NOTHING}

    public GuiWon(int score, int health, int steps, TerminalScreen screen){
        this.score = score;
        this.health = health;
        this.steps = steps;
        this.screen = screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawWonMenu(graphics);
        drawStats(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawWonMenu(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.putString(5, 2, "XXX  XXX  XXXXXX  XX   XX  ");
        graphics.putString(5, 3, "XXX  XXX  XX  XX  XX   XX  ");
        graphics.putString(5, 4, "   XX     XX  XX  XX   XX");
        graphics.putString(5, 5, "   XX     XX  XX  XX   XX");
        graphics.putString(5, 6, "   XX     XXXXXX  XXXXXXX");
        graphics.putString(1, 9, "X             X XXXXXX  XXX    X   XXX");
        graphics.putString(1, 10, " X     X     X  XX  XX  XX X   X   XXX");
        graphics.putString(1, 11, "  X   X X   X   XX  XX  XX  X  X   XXX");
        graphics.putString(1, 12, "   X X   X X    XX  XX  XX   X X");
        graphics.putString(1, 13, "    X     X     XXXXXX  XX    X    XXX");

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
        graphics.putString(26,32,"EXIT");
        graphics.putString(26,33, "[ESC]");
        graphics.putString(7, 32, "MENU");
        graphics.putString(8, 33, "[1]");

    }

    private void drawStats(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(3, 20, "Score: " + score + "\tHealth: " + health + "\tSteps: " + steps);
    }

    public GuiWon.COMMAND getNextCommand() throws IOException {
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
