package crossyroads;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.controller.AppController;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        new Application().start();
    }

    private void start() throws IOException {
        TerminalScreen screen = new ScreenFactory().createScreen(40, 36);
        AppController controller = new AppController(screen);
        controller.start();
    }
}
