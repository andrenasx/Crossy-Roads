package crossyroads.view;

import crossyroads.model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GuiGame implements Gui{
    private GameModel gameModel;
    private TerminalScreen screen;

    public GuiGame(GameModel gameModel, TerminalScreen screen){
        this.gameModel = gameModel;
        this.screen = screen;
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        drawScoreHealthLevel(graphics);
        drawGameMap(graphics);
        drawChicken(graphics, gameModel.getChicken());
        for (Element element: gameModel.getCurrentLevel().getAllElements()) drawElement(graphics, element);
        screen.refresh();
    }

    private void drawScoreHealthLevel(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(0, gameModel.getHeight(), "Score: " + gameModel.getScore() + "\tHealth: " + gameModel.getLives() + "\tLevel: " + gameModel.getCurrentLevelInt());
    }

    private void drawGameMap(TextGraphics graphics) {
        for (int y=0; y<gameModel.getHeight(); y++){
            graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor(y)));
            graphics.fillRectangle(new TerminalPosition(0,  y), new TerminalSize(gameModel.getWidth(), 1), ' ');
        }
    }

    private void drawChicken(TextGraphics graphics, Chicken chicken) {
        drawCharacter(graphics, chicken.getPosition(), "O", chicken.getColor());
    }

    private void drawElement(TextGraphics graphics, Element element) {
        String character="";
        if (element instanceof Coin) character="O";
        else if (element instanceof Car) character="<>";
        else if (element instanceof Truck) character="<==>";
        drawCharacter(graphics, element.getPosition(), character, element.getColor());
    }

    private void drawCharacter(TextGraphics graphics, Position position, String character, String color){
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor(position.getY())));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(position.getX(), position.getY(), character);
    }

    private String getBackgroundColor(int y){
        String color = gameModel.getCurrentLevel().getLevelTerrain().getBackground();

        switch (color.charAt(y)) {
            case 'g':
                return "#006600";
            case 'r':
                return "#C8C8C8";
            case 'd':   //finish line
                return "#013220";
            default:
                return "#000000";
        }
    }


    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.pollInput();

        if(input != null){
            KeyStroke clear = screen.pollInput(); //Clearing input
            while(clear != null){
                if(clear.getKeyType()==KeyType.Escape) return COMMAND.PAUSE;
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
                    return COMMAND.PAUSE;
                default:
                    return COMMAND.NOTHING;
            }
        }
        return COMMAND.NOTHING;
    }
}
