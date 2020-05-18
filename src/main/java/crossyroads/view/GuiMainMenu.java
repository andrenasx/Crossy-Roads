package crossyroads.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import crossyroads.model.GameModel;

import java.io.IOException;

public class GuiMainMenu {
    private GameModel gameModel;
    private TerminalScreen screen;
    public enum COMMAND {PLAY, HELP, EXIT, HIGHSCORES, EOF, NOTHING};

    public GuiMainMenu(GameModel gameModel) throws IOException{
        TerminalSize terminalSize = new TerminalSize(gameModel.getWidth(), gameModel.getHeight() + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.gameModel = gameModel;
    }

    public void setScreen(TerminalScreen screen){
        this.screen = screen;
    }

    public void draw() throws IOException {
        screen.clear();
        drawMainMenu();
        //drawButtons();
        screen.refresh();
    }

    private void drawMainMenu(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(gameModel.getWidth(), gameModel.getHeight()),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(13, 9, "Crossy Roads");
    }

    private void drawButtons(){};

    public GuiMainMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.pollInput();

        if(input != null){
            KeyStroke clear = screen.pollInput(); //Clearing input
            while(clear != null){
                if(clear.getKeyType()== KeyType.EOF) return GuiMainMenu.COMMAND.EOF;
                clear = screen.pollInput();
            }

            switch (input.getCharacter()){
                case 1:
                    return GuiMainMenu.COMMAND.PLAY;
                case 2:
                    return COMMAND.HELP;
                case 3:
                    return COMMAND.HIGHSCORES;
                case 4:
                    return COMMAND.EXIT;
                default:
                    return GuiMainMenu.COMMAND.NOTHING;
            }
        }
        return GuiMainMenu.COMMAND.NOTHING;
    }
}
