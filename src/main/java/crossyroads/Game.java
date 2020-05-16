package crossyroads;

import crossyroads.controller.GameController;
import crossyroads.model.GameMap;
import crossyroads.model.GameMapCreator;
import crossyroads.model.GameModel;
import crossyroads.view.Gui;

import java.io.IOException;

public class Game{
    private GameMap map;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        GameMapCreator creator = new GameMapCreator();
        map = creator.createGameMap(40,35);

        gui = new Gui(map);
        gui.draw();

        GameController controller = new GameController(gui, map);

        controller.start();
    }
}
