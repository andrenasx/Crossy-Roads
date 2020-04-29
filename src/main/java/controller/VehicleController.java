package controller;

import model.GameMap;
import model.Position;
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
        for (Vehicle vehicle: vehicles) {
            if (vehicle.getDirection().equals("left")) {
                vehicle.setPosition(vehicle.getPosition().left());
                if(vehicle.getPosition().getX() + vehicle.getLength() == 0){ //Make another function for these verifications
                    vehicle.setPosition(new Position(gameMap.getWidth(), vehicle.getPosition().getY()));
                }
            } else {
                vehicle.setPosition(vehicle.getPosition().right());
                if(vehicle.getPosition().getX() == gameMap.getWidth()){
                    vehicle.setPosition(new Position(0, vehicle.getPosition().getY()));
                }
            }
        }
        gameMap.notifyObservers();
    }

    public void start() {
        if ((System.currentTimeMillis() - startTime) % 1000 == 0)
            moveVehicles();
    }

    /*public boolean vehicleLeavesScreen(Position position){

    }*/
}
