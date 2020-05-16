package crossyroads;

import crossyroads.controller.GameController;
import crossyroads.model.GameModel;
import crossyroads.view.Gui;

import java.io.IOException;

public class Game{
    private GameModel gameModel;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        gameModel = new GameModel(40, 35, 1);
        gameModel.createLevels();

        gui = new Gui(gameModel);
        gui.draw();

        GameController controller = new GameController(gui, gameModel);

        controller.start();
    }
}
