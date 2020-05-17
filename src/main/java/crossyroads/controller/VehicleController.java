package crossyroads.controller;

import crossyroads.model.*;

import java.util.List;

public class VehicleController {
    private GameModel gameModel;
    private long startTime;


    public VehicleController(GameModel gameModel) {
        this.gameModel = gameModel;
        this.startTime = System.currentTimeMillis();
    }

    public void moveVehicles(int step) {
        List<Vehicle> vehicles = gameModel.getCurrentLevel().getVehicles();
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
    }

    public void vehicleLeavesScreen(Vehicle vehicle){
        if(vehicle.getDirection().equals("left")){
            if(vehicle.getPosition().getX() + vehicle.getLength() == 0)
                vehicle.setPosition(new Position(gameModel.getWidth(), vehicle.getPosition().getY()));
        }
        else{
            if(vehicle.getPosition().getX() == gameModel.getWidth())
                vehicle.setPosition(new Position(-vehicle.getLength(), vehicle.getPosition().getY()));
        }
    }

    public void checkChickenCollision(Vehicle vehicle) {
        Chicken chicken = gameModel.getCurrentLevel().getChicken();
        if(vehicle.checkCollision(chicken.getPosition())){
            chicken.removeLife();
            gameModel.getCurrentLevel().resetChickenPosition();
        }
    }

    public void start(int step) {
        moveVehicles(step);
    }
}
