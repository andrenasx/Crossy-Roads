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
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import crossyroads.model.GameModel;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiHelpMenu {
    private GameModel gameModel;
    private TerminalScreen screen;
    public enum COMMAND {BACK, NOTHING, EOF};

    public GuiHelpMenu(GameModel gameModel) throws IOException {
        TerminalSize terminalSize = new TerminalSize(gameModel.getWidth(), gameModel.getHeight() + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Font font = new Font("courier", Font.PLAIN, 25);
        Font loadedFont = font.deriveFont(Font.PLAIN, 15);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.gameModel = gameModel;
    }

    public void draw() throws IOException {
        screen.clear();
        drawHelpMenu();
        drawButton();
        screen.refresh();
    }

    private void drawHelpMenu(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006600"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(gameModel.getWidth(), gameModel.getHeight()+1),
                ' '
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(17, 5, "HELP");
        List<String> instrutions = new ArrayList<>();
        String inst1 = "-To move use the arrow of the keyboard";
        instrutions.add(inst1);
        String inst2 = "-To exit the game click ESCAPE";
        instrutions.add(inst2);
        String inst3 = "-Collect all the coins";
        instrutions.add(inst3);
        String inst4 = "-Reach the finish line on the top";
        instrutions.add(inst4);
        String inst5 = "-You lose health when hitting a vehicle";
        instrutions.add(inst5);
        String inst6 = "------------>Have fun!!!!!!<------------";
        instrutions.add(inst6);
        int row = 9;
        for(int i = 0; i < instrutions.size(); i++){
            graphics.putString(0, row, instrutions.get(i));
            row += 2;
        }

    }

    private void drawButton(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C8C8C8"));
        graphics.fillRectangle(
                    new TerminalPosition(29, 32),
                    new TerminalSize(8, 2),
                    ' ');

        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#25221e"));
        graphics.putString(31,32,"BACK");
        graphics.putString(32,33,"[1]");
    }

    public GuiHelpMenu.COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.pollInput();

        if(input != null){
            KeyStroke clear = screen.pollInput(); //Clearing input
            while(clear != null){
                if(clear.getKeyType()== KeyType.EOF) return GuiHelpMenu.COMMAND.EOF;
                clear = screen.pollInput();
            }

            switch (input.getKeyType()){
                case EOF:
                    return COMMAND.EOF;
                case Character:
                    if(input.getCharacter() == '1')
                    return COMMAND.BACK;
                default:
                    return GuiHelpMenu.COMMAND.NOTHING;
            }
        }
        return GuiHelpMenu.COMMAND.NOTHING;
    }
}
