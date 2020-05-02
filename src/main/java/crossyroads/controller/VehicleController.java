package crossyroads.controller;

import crossyroads.model.Chicken;
import crossyroads.model.GameMap;
import crossyroads.model.Position;
import crossyroads.model.Vehicle;

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
                    vehicle.setPosition(new Position(-vehicle.getLength(), vehicle.getPosition().getY()));
                }
            }
            checkChickenCollision(vehicle);
        }
        gameMap.notifyObservers();
    }

    public void checkChickenCollision(Vehicle vehicle) {
        Chicken chicken = gameMap.getChicken();
        if(vehicle.checkCollision(chicken.getPosition())){
            chicken.removeLife();
            gameMap.resetChickenPosition();
        }
    }

    public void start() {
        if ((System.currentTimeMillis() - startTime) % 200 == 0) {
            moveVehicles();
        }
    }

}
