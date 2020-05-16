package crossyroads.controller;

import crossyroads.model.MusicPlayer;
import crossyroads.model.GameMap;
import crossyroads.view.Gui;

import java.io.IOException;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private Gui gui;
    private GameMap map;

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
            Gui.COMMAND command = gui.getNextCommand();
            if(command == Gui.COMMAND.EOF) break;
            chickenController.start(command);
            vehicleController.start();
            gui.draw();
        }

        player.stopMusic();
        System.exit(0);
    }

}

