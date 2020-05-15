package crossyroads.controller;

import crossyroads.model.MusicPlayer;
import crossyroads.model.GameMap;
import crossyroads.model.Vehicle;
import crossyroads.view.Gui;

import java.io.IOException;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private Gui gui;
    private GameMap map;
    private final int FPS = 5;
    private int step = 0;

    public GameController(Gui gui, GameMap map) {
        this.gui = gui;
        this.map = map;

        this.chickenController = new ChickenController(map);
        this.vehicleController = new VehicleController(map);
    }

    public void start() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/piu.wav");
        player.startMusic();

        while(!map.isGameFinished()) {
            long time = System.currentTimeMillis();
            step++;

            Gui.COMMAND command = gui.getNextCommand();
            if(command == Gui.COMMAND.EOF) break;
            chickenController.start(command);
            vehicleController.start();

            vehicleController.moveVehicles(step);

            long elapsed = System.currentTimeMillis() - time;

            try {
                Thread.sleep(1000 / FPS - elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        player.stopMusic();
        System.exit(0);
    }



}

