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
    public enum COMMAND {PLAY, HELP, EXIT, HIGHSCORES, NOTHING};

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
        graphics.putString(14, 5, "CROSSY ROADS");
    }

    private void drawButtons(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        int row = 10;
        for(int i = 0; i <= 3; i++){
            graphics.fillRectangle(
                    new TerminalPosition(14, row),
                    new TerminalSize(12, 2),
                    ' ');
            row+=5;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(18,10,"PLAY");
        graphics.putString(18,11,"[1]");
        graphics.putString(18,15, "HELP");
        graphics.putString(18,16,"[2]");
        graphics.putString(15,20,"HIGHSCORES");
        graphics.putString(18,21,"[3]");
        graphics.putString(18,25,"EXIT");
        graphics.putString(18,26,"[4]");

    };

    public GuiMainMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getCharacter()){
            case '1':
                return GuiMainMenu.COMMAND.PLAY;
            case '2':
                return COMMAND.HELP;
            case '3':
                return COMMAND.HIGHSCORES;
            case '4':
                return COMMAND.EXIT;
            default:
                return GuiMainMenu.COMMAND.NOTHING;
        }
    }
}
