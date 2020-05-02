package crossyroads.controller;

import crossyroads.model.GameMap;
import crossyroads.view.Gui;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private Gui gui;
    private GameMap map;

    public GameController(Gui gui, GameMap map) {
        this.gui = gui;
        this.map = map;

        this.chickenController = new ChickenController(gui, map);
        this.vehicleController = new VehicleController(map);
    }

    public void start(){
        while(!gui.verifyFinnishLine()) {
            chickenController.start();
            vehicleController.start();
        }
        System.exit(0);
    }

}

