import Controller.GameController;
import Model.GameMap;
import Model.GameMapCreator;
import Model.GameMapObserver;
import View.Gui;

import java.io.IOException;
import java.util.Map;

public class Game implements GameMapObserver {
    private GameMap map;
    private Gui gui;
    private long startTime;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        startTime = System.currentTimeMillis();
        GameMapCreator creator = new GameMapCreator();
        map = creator.createGameMap(40,35);
        map.addObserver(this);

        gui = new Gui(map);
        gui.draw();

        GameController commands = new GameController(gui, map);
        //commands.execute();


        while(!map.isGameFinished()){
            commands.execute();
            if ((System.currentTimeMillis() - startTime) % 1000 == 0)
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
