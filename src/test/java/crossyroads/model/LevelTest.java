package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LevelTest {
    Random rand;
    int x, y;
    Terrain terrain;
    Level level;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(50);
        y = rand.nextInt(70);

        terrain = mock(Terrain.class);
        level = new Level(x, y, terrain);
    }

    @Test
    public void getLevelSizeTest(){
        assertEquals(x, level.getWidth());
        assertEquals(y, level.getHeight());
    }

    @Test
    public void addElementTest(){
        Coin coin = new Coin(10,10);
        Coin coin1 = new Coin(20,20);
        Coin coin2 = new Coin(25,20);
        Car car = new Car(30,30,"right");
        Truck truck = new Truck (10,30, "left");

        level.addElement(coin);
        level.addElement(coin1);
        level.addElement(coin2);
        level.addElement(car);
        level.addElement(truck);

        List<Coin> coins = new ArrayList<>();
        coins.add(coin);
        coins.add(coin1);
        coins.add(coin2);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(truck);

        assertEquals(coins, level.getCoins());
        assertEquals(vehicles, level.getVehicles());

    }

    @Test
    public void getAllElementsTest(){
        Coin coin = new Coin(10,10);
        Coin coin1 = new Coin(20,20);
        Car car = new Car(30,30,"right");
        Truck truck = new Truck (10,30, "left");

        level.addElement(coin);
        level.addElement(coin1);
        level.addElement(car);
        level.addElement(truck);

        List<Element> elements = new ArrayList<>();
        elements.add(car);
        elements.add(truck);
        elements.add(coin);
        elements.add(coin1);

        assertEquals(elements, level.getAllElements());
    }

    @Test
    public void getTerrain(){
        assertEquals(terrain, level.getLevelTerrain());
    }
}
