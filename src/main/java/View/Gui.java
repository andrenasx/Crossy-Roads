package View;

import Controller.*;
import Model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;

public class Gui {
    private final GameMap gameMap;
    private final TerminalScreen screen;
    public enum COMMAND {UP, DOWN, LEFT, RIGHT, NOTHING};

    public Gui(GameMap map) throws IOException {
        TerminalSize terminalSize = new TerminalSize(map.getWidth(), map.getHeight() + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.gameMap = map;
    }

    public void draw() throws IOException {
        screen.clear();

        drawGameMap();
        drawScore();
        for (Element element: gameMap.getAllElements()) drawElement(element);
        screen.refresh();
    }

    private void drawScore() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(0, gameMap.getHeight(), "Score: " + gameMap.getScore() + "\tHealth: " + gameMap.getLives());
    }

    private void drawGameMap() {
        TextGraphics graphics = screen.newTextGraphics();
        int x=0;
        int y=0;
        for (String terrain: gameMap.getGameTerrain()){
            if(String.valueOf(terrain.charAt(0)).equals("g")){
                graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
            }
            else if(String.valueOf(terrain.charAt(0)).equals("r")){
                graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
            }
            else if(String.valueOf(terrain.charAt(0)).equals("d")){ //finish line
                graphics.setBackgroundColor(TextColor.Factory.fromString("#013220"));
            }
            graphics.fillRectangle(new TerminalPosition(x,  y), new TerminalSize(gameMap.getWidth(), 1), ' ');
            y++;
        }
    }

    private void drawElement(Element element) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(element.getColor()));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(element.getPosition().getX(), element.getPosition().getY(), element.getCharacter());
    }

    public COMMAND getNextCommand() throws IOException {
            KeyStroke input = screen.pollInput();
            if(input != null){
                switch (input.getKeyType()){
                    case ArrowUp:
                        return COMMAND.UP;
                    case ArrowDown:
                        return COMMAND.DOWN;
                    case ArrowRight:
                        return COMMAND.RIGHT;
                    case ArrowLeft:
                        return COMMAND.LEFT;
                    default:
                        return COMMAND.NOTHING;
                }
            }
            return COMMAND.NOTHING;
    }
}