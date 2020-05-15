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

    public void moveVehicles(int step) {
        List<Vehicle> vehicles = gameMap.getVehicles();
        for (Vehicle vehicle: vehicles) {
            if (step % vehicle.getSpeed() == 0) {
                if (vehicle.getDirection().equals("left")) {
                    vehicle.setPosition(vehicle.getPosition().left());
                } else {
                    vehicle.setPosition(vehicle.getPosition().right());
                }
                vehicleLeavesScreen(vehicle);
                checkChickenCollision(vehicle);
            }
        }
        gameMap.notifyObservers();
    }

    public void vehicleLeavesScreen(Vehicle vehicle){
        if(vehicle.getDirection().equals("left")){
            if(vehicle.getPosition().getX() + vehicle.getLength() == 0)
                vehicle.setPosition(new Position(gameMap.getWidth(), vehicle.getPosition().getY()));
        }
        else{
            if(vehicle.getPosition().getX() == gameMap.getWidth())
                vehicle.setPosition(new Position(-vehicle.getLength(), vehicle.getPosition().getY()));
        }
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
            moveVehicles(0);
        }
    }

}
