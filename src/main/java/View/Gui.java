package View;

import Model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

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

        for (Element element: gameMap.getAllElements()) drawElement(element);
        screen.refresh();
    }

    private void drawGameMap() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
               new TerminalSize(gameMap.getWidth(), gameMap.getHeight()), ' ');
    }

    private void drawElement(Element element) {
        if (element instanceof Chicken) drawCharacter(element.getPosition(), "O", "#FFFFFF");
        if (element instanceof Car) drawCharacter(element.getPosition(), "C", "#FFFFFF");
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(position.getX(), position.getY(), character);
    }
}
