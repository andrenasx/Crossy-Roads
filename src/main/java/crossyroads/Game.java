package crossyroads;

import crossyroads.controller.AppController;
import java.io.IOException;

public class Game{
    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        AppController controller = new AppController();
        controller.start();
    }
}
