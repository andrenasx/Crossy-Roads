package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiMainMenu {
    private TerminalScreen screen;
    public enum COMMAND {PLAY, HELP, EXIT, HIGHSCORES, NOTHING}

    public GuiMainMenu() throws IOException {
        this.screen = ScreenFactory.getScreen();
    }

    public void draw() throws IOException {
        screen.clear();
        drawMainMenu();
        drawButtons();
        screen.refresh();
    }

    private void drawMainMenu(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.putString(1, 1, "XXXX XXXXX  XXXX XXXXX XXXXX XXX  XXX");
        graphics.putString(1, 2, "X    XX  X  X  X XX    XX    XXX  XXX  ");
        graphics.putString(1, 3, "X    XXXX   X  X XXXXX XXXXX    XX  ");
        graphics.putString(1, 4, "X    XX  X  X  X    XX    XX    XX ");
        graphics.putString(1, 5, "XXXX XX   X XXXX XXXXX XXXXX    XX");
        graphics.putString(2, 8, "XXXXX   XXXXX     XXX     XXXX  XXXXX");
        graphics.putString(2, 9, "XX  X   X   X    X   X    X  X  XX");
        graphics.putString(2, 10, "XXXX    X   X   X XXX X   X  X  XXXXX");
        graphics.putString(2, 11, "XX  X   X   X  XX     XX  X  X     XX");
        graphics.putString(2, 12, "XX   X  XXXXX XX       XX XXX   XXXXX");
    }

    private void drawButtons(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        int row = 15;
        for(int i = 0; i <= 3; i++){
            graphics.fillRectangle(
                    new TerminalPosition(14, row),
                    new TerminalSize(12, 2),
                    ' ');
            row+=5;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(18,15,"PLAY");
        graphics.putString(19,16,"[1]");
        graphics.putString(18,20, "HELP");
        graphics.putString(19,21,"[2]");
        graphics.putString(15,25,"HIGHSCORES");
        graphics.putString(19,26,"[3]");
        graphics.putString(18,30,"EXIT");
        graphics.putString(17,31,"[ESC]");

    }

    public GuiMainMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getKeyType()){
            case Character:
                if(input.getCharacter() == '1')
                    return GuiMainMenu.COMMAND.PLAY;
                else if(input.getCharacter() == '2')
                    return COMMAND.HELP;
                else if (input.getCharacter() == '3')
                    return COMMAND.HIGHSCORES;
            case Escape:
                return COMMAND.EXIT;
            default:
                return GuiMainMenu.COMMAND.NOTHING;
        }
    }
}
