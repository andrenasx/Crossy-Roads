import Model.GameMap;
import Model.GameMapCreator;
import View.Gui;

import java.io.IOException;
import java.util.Map;

public class Game {
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
    }
}
