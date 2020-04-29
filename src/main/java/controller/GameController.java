package controller;

import model.GameMap;
import view.Gui;

import java.io.IOException;

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
        chickenController.start();
        vehicleController.start();
    }

}

