import controller.GameController;
import model.GameMap;
import model.GameMapCreator;
import model.GameMapObserver;
import view.Gui;

import java.io.IOException;

public class Game implements GameMapObserver {
    private GameMap map;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        GameMapCreator creator = new GameMapCreator();
        map = creator.createGameMap(40,35);
        map.addObserver(this);

        gui = new Gui(map);
        gui.draw();

        GameController controller = new GameController(gui, map);

        controller.start();
    }

    @Override
    public void gameMapChanged() {
        try {
            gui.draw();
        } catch (IOException e) {
            // Nothing to do if drawing fails
        }
    }
}
