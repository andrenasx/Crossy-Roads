package crossyroads.controller;

import crossyroads.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VehicleControllerTest {
    @Test
    public void moveVehicleTest(){
        List<Vehicle> vehicles = new ArrayList<>();
        Car car1 = new Car(1, 2, "left");
        Car car2 = new Car(3, 6, "right");
        Car car3 = new Car(40-1, 2, "right");
        Car car4 = new Car(-1, 2, "left");
        vehicles.add(car1);
        vehicles.add(car2);
        vehicles.add(car3);
        vehicles.add(car4);

        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getVehicles()).thenReturn(vehicles);

        GameModel gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.getWidth()).thenReturn(40);
        Mockito.when(gameModel.getHeight()).thenReturn(35);
        Mockito.when(gameModel.getChicken()).thenReturn(new Chicken(1,2));
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);

        VehicleController vehicleController = new VehicleController(gameModel);
        Position position1 = car1.getPosition();
        Position position2 = car2.getPosition();
        vehicleController.moveVehicles(6);
        assertEquals(car1.getPosition(), new Position(position1.getX()-1, position1.getY()));
        assertEquals(car2.getPosition(), new Position(position2.getX()+1, position2.getY()));
        vehicleController.vehicleLeavesScreen(car4);
        assertEquals(car3.getPosition(), new Position(-car3.getLength(), car3.getPosition().getY()));
        assertEquals(car4.getPosition(), new Position(gameModel.getWidth(), car4.getPosition().getY()));
    }

    @Test
    public void checkChickenCollisionTest(){
        List<Vehicle> vehicles = new ArrayList<>();
        Car car = new Car(1, 2, "right");
        vehicles.add(car);
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getVehicles()).thenReturn(vehicles);

        GameModel gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.getWidth()).thenReturn(40);
        Mockito.when(gameModel.getHeight()).thenReturn(35);
        Mockito.when(gameModel.getChicken()).thenReturn(new Chicken(1,2));
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);

        VehicleController vehicleController = new VehicleController(gameModel);
        Chicken chicken = gameModel.getChicken();
        int life = chicken.getLives();
        Position position = chicken.getPosition();
        //chicken.setPosition(new Position(1, 2));
        vehicleController.checkChickenCollision(car);
        assertEquals(life-1, chicken.getLives());
        //assertEquals(chicken.getPosition(), new Position(gameModel.getWidth()/2, gameModel.getHeight()-1));
    }

    @Test
    public void startTest(){
        GameModel gameModel = Mockito.mock(GameModel.class);
        Level level = Mockito.mock(Level.class);
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);
        VehicleController vehicleController = new VehicleController(gameModel);
        vehicleController.start(1);
        Mockito.verify(gameModel, Mockito.times(1)).getCurrentLevel();
    }
}
