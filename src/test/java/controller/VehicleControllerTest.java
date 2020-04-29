package controller;

import model.Car;
import model.GameMap;
import model.Position;
import model.Vehicle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleControllerTest {
    @Test
    public void moveVehicleTest(){
        GameMap map = new GameMap(40, 35);
        VehicleController vehicleController = new VehicleController(map);
        Car car1 = new Car(1, 2, "left");
        Car car2 = new Car(3, 6, "right");
        map.addElement(car1);
        map.addElement(car2);
        Position position1 = car1.getPosition();
        Position position2 = car2.getPosition();
        vehicleController.moveVehicles();
        assertEquals(car1.getPosition(), new Position(position1.getX()-1, position1.getY()));
        assertEquals(car2.getPosition(), new Position(position2.getX()+1, position2.getY()));

    }
}
