package crossyroads.view;

import crossyroads.model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;

//import static com.sun.tools.doclint.Entity.and;

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
        for (int y=0; y<gameMap.getHeight(); y++){
            graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor(y)));
            graphics.fillRectangle(new TerminalPosition(0,  y), new TerminalSize(gameMap.getWidth(), 1), ' ');
        }
    }

    private void drawElement(Element element) {
        String character="";
        if (element instanceof Chicken) character="O";
        if (element instanceof Coin) character="O";
        if (element instanceof Car) character="<>";
        if (element instanceof Truck) character="<==>";
        drawCharacter(element.getPosition(), character, element.getColor());
    }

    private void drawCharacter(Position position, String character, String color){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor(position.getY())));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(position.getX(), position.getY(), character);
    }

    private String getBackgroundColor(int y){
        String color = gameMap.getGameTerrain();

        switch (color.charAt(y)) {
            case 'g':
                return "#006600";
            case 'r':
                return "#C8C8C8";
            case 'd':   //finish line
                return "#013220";
        }
        return "000000";
    }

    public boolean verifyFinnishLine() {
        return (getBackgroundColor(gameMap.getChicken().getPosition().getY()).equals("#013220") & gameMap.getCoins().isEmpty()) || gameMap.getChicken().isDead();
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