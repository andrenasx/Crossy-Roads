package crossyroads.controller;

import crossyroads.model.GameModel;
import crossyroads.model.MusicPlayer;
import crossyroads.model.GameMap;
import crossyroads.view.Gui;

import java.io.IOException;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private Gui gui;
    private GameModel gameModel;

    public GameController(Gui gui, GameModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;

        this.chickenController = new ChickenController(gameModel.getCurrentLevel());
        this.vehicleController = new VehicleController(gameModel.getCurrentLevel());
    }

    public void start() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/piu.wav");
        player.startMusic();

        while(!gameModel.getCurrentLevel().isGameFinished()) {
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

