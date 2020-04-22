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
        File file = new File("src/main/java/map.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int x = 0;
            int y = 0;
            while ((st = br.readLine()) != null){
                if(String.valueOf(st.charAt(0)).equals("g")){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
                    graphics.fillRectangle(
                            new TerminalPosition(x,  y),
                            new TerminalSize(gameMap.getWidth(), 1), ' ');
                    y++;
                }
                else if(String.valueOf(st.charAt(0)).equals("r")){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
                    graphics.fillRectangle(
                            new TerminalPosition(x,  y),
                            new TerminalSize(gameMap.getWidth(), 1), ' ');
                    y++;
                }
                else if(String.valueOf(st.charAt(0)).equals("d")){ //finish line
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#013220"));
                    graphics.fillRectangle(
                            new TerminalPosition(x,  y),
                            new TerminalSize(gameMap.getWidth(), 1), ' ');
                    y++;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*private void drawElement(Element element) {
        if (element instanceof Chicken) drawCharacter(element.getPosition(), "O", "#FFFFFF", false);
        if (element instanceof Car) drawCharacter(element.getPosition(), "CC", "#FF0000", true);
        if (element instanceof Truck) drawCharacter(element.getPosition(), "TTTT", "#0000FF", true);
        if (element instanceof Coin) drawCharacter(element.getPosition(), "S", "#FFFF00", false);
    }*/

    private void drawElement(Element element) {
        TextGraphics graphics = screen.newTextGraphics();
        /*boolean road=false;
        if(road){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        }
        else
            graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));*/
        graphics.setForegroundColor(TextColor.Factory.fromString(element.getColor()));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(element.getPosition().getX(), element.getPosition().getY(), element.getCharacter());
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.pollInput();

        if (input != null){
            if (input.getKeyType() == KeyType.EOF) return new QuitCommand(gameMap, screen);
            if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(gameMap, screen);
            if (input.getKeyType() == KeyType.ArrowDown) return new MoveChickenDown(gameMap);
            if (input.getKeyType() == KeyType.ArrowUp) return new MoveChickenUp(gameMap);
            if (input.getKeyType() == KeyType.ArrowLeft) return new MoveChickenLeft(gameMap);
            if (input.getKeyType() == KeyType.ArrowRight) return new MoveChickenRight(gameMap);
        }

        return new DoNothingCommand();
    }
}