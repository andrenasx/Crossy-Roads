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

    public GuiMainMenu(TerminalScreen screen){
        this.screen = screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawMainMenu(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawMainMenu(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(4, 1, "  _____");
        graphics.putString(4, 2, " / ____|");
        graphics.putString(4, 3, "| |     _ __ ___  ___ ___ _   _ ");
        graphics.putString(4, 4, "| |    | '__/ _ \\/ __/ __| | | |");
        graphics.putString(4, 5, "| |____| | | (_) \\__ \\__ \\ |_| |");
        graphics.putString(4, 6, " \\_____|_|  \\___/|___/___/\\__, |");
        graphics.putString(4, 7, "|  __ \\               | |  __/ |");
        graphics.putString(4, 8, "| |__) |___   __ _  __| |_|___/ ");
        graphics.putString(4, 9, "|  _  // _ \\ / _` |/ _` / __|   ");
        graphics.putString(4, 10, "| | \\ \\ (_) | (_| | (_| \\__ \\");
        graphics.putString(4, 11, "|_|  \\_\\___/ \\__,_|\\__,_|___/");
    }

    private void drawButtons(TextGraphics graphics){
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
        graphics.putString(18,16,"[1]");
        graphics.putString(18,20, "HELP");
        graphics.putString(18,21,"[2]");
        graphics.putString(15,25,"HIGHSCORES");
        graphics.putString(18,26,"[3]");
        graphics.putString(18,30,"EXIT");
        graphics.putString(18,31,"[ESC]");

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
