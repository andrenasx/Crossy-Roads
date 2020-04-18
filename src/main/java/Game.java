import Controller.Command;
import Model.GameMap;
import Model.GameMapCreator;
import Model.GameMapObserver;
import View.Gui;

import java.io.IOException;
import java.util.Map;

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

        while(!map.isGameFinished()){
            Command command = gui.getNextCommand();
            command.execute();
            map.moveVehicles();
        }
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
