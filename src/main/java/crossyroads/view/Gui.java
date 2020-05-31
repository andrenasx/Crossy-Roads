package crossyroads.view;

import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public interface Gui {
    enum COMMAND {NOTHING, MENU, PLAY, HELP, HIGHSCORES, EXIT, UP, DOWN, LEFT, RIGHT, PAUSE}

    TerminalScreen getScreen();
    void draw() throws IOException;
    COMMAND getNextCommand() throws IOException;
}
