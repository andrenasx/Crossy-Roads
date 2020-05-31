package crossyroads.controller;

import crossyroads.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VehicleControllerTest {
    GameModel gameModel;

    @Before
    public void init(){
        gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.getWidth()).thenReturn(40);
        Mockito.when(gameModel.getHeight()).thenReturn(35);
        Mockito.when(gameModel.getChicken()).thenReturn(new Chicken(1,2));
    }

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
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);

        VehicleController vehicleController = new VehicleController(gameModel);
        Chicken chicken = gameModel.getChicken();
        int life = chicken.getLives();
        vehicleController.checkChickenCollision(car);
        assertEquals(life-1, chicken.getLives());
    }

    @Test
    public void startTest(){
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));
        VehicleController vehicleController = new VehicleController(gameModel);
        vehicleController.start(1);
        Mockito.verify(gameModel, Mockito.times(1)).getCurrentLevel();
    }
}
