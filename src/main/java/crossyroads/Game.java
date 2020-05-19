package crossyroads;

import crossyroads.controller.GameController;
import crossyroads.model.GameModel;
import crossyroads.view.Gui;
import crossyroads.view.GuiSquare;

import java.io.IOException;

public class Game{
    private GameModel gameModel;
    private GuiSquare gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        gameModel = new GameModel(40, 35, 5);
        gameModel.createLevels();

        gui = new GuiSquare(gameModel);
        gui.draw();

        GameController controller = new GameController(gui, gameModel);

        controller.start();
    }
}
