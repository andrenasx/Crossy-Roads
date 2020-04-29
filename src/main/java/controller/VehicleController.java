package controller;

import model.GameMap;
import model.Vehicle;

import java.util.List;

public class VehicleController {
    private GameMap gameMap;
    private long startTime;


    public VehicleController(GameMap gameMap) {
        this.gameMap = gameMap;
        this.startTime = System.currentTimeMillis();
    }

    public void moveVehicles() {
        List<Vehicle> vehicles = gameMap.getVehicles();
        for (Vehicle vehicle: vehicles){
            if(vehicle.getDirection().equals("left"))
                vehicle.setPosition(vehicle.getPosition().left());
            else
                vehicle.setPosition(vehicle.getPosition().right());
        }
        gameMap.notifyObservers();
    }

    public void start() {
        if ((System.currentTimeMillis() - startTime) % 1000 == 0)
            moveVehicles();
    }

}
