package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.model.Highscore;
import crossyroads.model.Score;
import crossyroads.model.Terrain;

import java.io.IOException;
import java.util.List;

public class GuiHighscoreMenu {
    private TerminalScreen screen;
    public enum COMMAND {PLAY, BACK, NOTHING}

    public GuiHighscoreMenu(TerminalScreen screen){
        this.screen = screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawHighscoreMenu(graphics);
        drawHighscores(graphics);
        drawButtons(graphics);
        screen.refresh();
    }

    private void drawHighscoreMenu(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(40, 36),
                ' '
        );

        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(0, 3, " _  _ _      _");
        graphics.putString(0, 4, "| || (_)__ _| |_  ___ __ ___ _ _ ___ ___");
        graphics.putString(0, 5, "| __ | / _` | ' \\(_-</ _/ _ \\ '_/ -_|_-<");
        graphics.putString(0, 6, "|_||_|_\\__, |_||_/__/\\__\\___/_| \\___/__/");
        graphics.putString(0, 7, "       |___/");
    }

    private void drawHighscores(TextGraphics graphics) {
        graphics.putString(5, 13, "Level   |   Coins   |   Steps");
        Highscore highscore = new Highscore("highscores.txt", "main");
        List<Score> highscores = highscore.getHighscores();
        int row = 15;
        for (Score score: highscores){
            graphics.putString(7, row, Integer.toString(score.getLevel()));
            graphics.putString(18, row, Integer.toString(score.getCoins()));
            graphics.putString(30, row, Integer.toString(score.getSteps()));
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

    public GuiHighscoreMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        switch (input.getKeyType()){
            case Escape:
                return GuiHighscoreMenu.COMMAND.BACK;
            case Character:
                if(input.getCharacter() == '1')
                    return GuiHighscoreMenu.COMMAND.PLAY;
            default:
                return GuiHighscoreMenu.COMMAND.NOTHING;
        }
    }
}
