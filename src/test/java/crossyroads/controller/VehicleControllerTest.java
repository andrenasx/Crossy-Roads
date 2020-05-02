package crossyroads.controller;

import crossyroads.Game;
import crossyroads.model.Car;
import crossyroads.model.Chicken;
import crossyroads.model.GameMap;
import crossyroads.model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class VehicleControllerTest {
    @Test
    public void moveVehicleTest(){
        GameMap map = new GameMap(40, 35);
        VehicleController vehicleController = new VehicleController(map);
        Car car1 = new Car(1, 2, "left");
        Car car2 = new Car(3, 6, "right");
        Car car3 = new Car(map.getWidth()-1, 2, "right");
        map.addElement(car1);
        map.addElement(car2);
        map.addElement(car3);
        Position position1 = car1.getPosition();
        Position position2 = car2.getPosition();
        vehicleController.moveVehicles();
        assertEquals(car1.getPosition(), new Position(position1.getX()-1, position1.getY()));
        assertEquals(car2.getPosition(), new Position(position2.getX()+1, position2.getY()));
        assertEquals(car3.getPosition(), new Position(-car3.getLength(), car3.getPosition().getY()));
    }

    @Test
    public void checkChickenCollisionTest(){
        GameMap map = new GameMap(40, 35);
        VehicleController vehicleController = new VehicleController(map);
        Car car = new Car(1, 2, "right");
        int life = map.getLives();
        Chicken chicken = map.getChicken();
        Position position = chicken.getPosition();
        chicken.setPosition(new Position(1, 2));
        vehicleController.checkChickenCollision(car);
        assertEquals(life-1, chicken.getLives());
        assertEquals(chicken.getPosition(), new Position(map.getWidth()/2, map.getHeight()-1));


    }
}
