package crossyroads.view;

import crossyroads.model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiSquare {
    private GameModel gameModel;
    private TerminalScreen screen;
    public enum COMMAND {UP, DOWN, LEFT, RIGHT, NOTHING, EOF};

    public GuiSquare(GameModel gameModel) throws IOException {
        this.screen = ScreenFactory.getScreen();
        this.gameModel = gameModel;
    }

    public void draw() throws IOException {
        screen.clear();

        drawGameMap();
        drawScoreHealthLevel();
        drawChicken(gameModel.getChicken());
        for (Element element: gameModel.getCurrentLevel().getAllElements()) drawElement(element);
        screen.refresh();
    }

    private void drawScoreHealthLevel() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(0, gameModel.getHeight(), "Score: " + gameModel.getScore() + "\tHealth: " + gameModel.getLives() + "\tLevel: " + gameModel.getCurrentLevelInt());
    }

    private void drawGameMap() {
        TextGraphics graphics = screen.newTextGraphics();
        for (int y=0; y<gameModel.getHeight(); y++){
            graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor(y)));
            graphics.fillRectangle(new TerminalPosition(0,  y), new TerminalSize(gameModel.getWidth(), 1), ' ');
        }
    }

    private void drawChicken(Chicken chicken) {
        drawCharacter(chicken.getPosition(), "O", chicken.getColor());
    }

    private void drawElement(Element element) {
        String character="";
        if (element instanceof Coin) character="O";
        else if (element instanceof Car) character="<>";
        else if (element instanceof Truck) character="<==>";
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
        String color = gameModel.getCurrentLevel().getLevelBackground();

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


    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.pollInput();

        if(input != null){
            KeyStroke clear = screen.pollInput(); //Clearing input
            while(clear != null){
                if(clear.getKeyType()==KeyType.EOF) return COMMAND.EOF;
                clear = screen.pollInput();
            }

            switch (input.getKeyType()){
                case ArrowUp:
                    return COMMAND.UP;
                case ArrowDown:
                    return COMMAND.DOWN;
                case ArrowRight:
                    return COMMAND.RIGHT;
                case ArrowLeft:
                    return COMMAND.LEFT;
                case Escape:
                    return COMMAND.EOF;
                default:
                    return COMMAND.NOTHING;
            }
        }
        return COMMAND.NOTHING;
    }
}
