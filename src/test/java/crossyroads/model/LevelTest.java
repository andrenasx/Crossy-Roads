package crossyroads.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class LevelTest {
    @Test
    public void getGameMapSizeTest(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);

        Level game = new Level(1, x,y);
        assertEquals(x,game.getWidth());
        assertEquals(y,game.getHeight());
    }

    @Test
    public void addElementTest(){
        Level game = new Level(1, 30,70);

        Coin coin = new Coin(10,10,1);
        Coin coin1 = new Coin(20,20,1);
        Coin coin2 = new Coin(25,20,1);
        Car car = new Car(30,30,"right");
        Truck truck = new Truck (10,30, "left");

        game.addElement(coin);
        game.addElement(coin1);
        game.addElement(coin2);
        game.addElement(car);
        game.addElement(truck);

        List<Coin> coins = new ArrayList<>();
        coins.add(coin);
        coins.add(coin1);
        coins.add(coin2);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(truck);

        assertEquals(coins,game.getCoins());
        assertEquals(vehicles,game.getVehicles());

    }

    @Test
    public void getAllElementsTest(){
        Level game = new Level(1, 30,70);

        Coin coin = new Coin(10,10,1);
        Coin coin1 = new Coin(20,20,1);
        Car car = new Car(30,30,"right");
        Truck truck = new Truck (10,30, "left");

        game.addElement(coin);
        game.addElement(coin1);
        game.addElement(car);
        game.addElement(truck);

        List<Element> elements = new ArrayList<>();
        elements.add(car);
        elements.add(truck);
        elements.add(coin);
        elements.add(coin1);

        assertEquals(elements,game.getAllElements());
    }

    /*@Test
    public void resetChickenTest(){
        Level map = new Level(1, 14, 40);
        Chicken chicken = map.getChicken();
        chicken.setPosition(new Position(1, 2));
        map.resetChickenPosition();
        assertEquals(chicken.getPosition(), new Position(14/2, 40-1));
    }

    @Test
    public void getScoreTest(){
        Level map = new Level(1, 15, 30);
        Chicken chicken = map.getChicken();
        chicken.raiseScore(3);
        assertEquals(3, map.getScore());
    }

    @Test
    public void getLivesTest(){
        Level map = new Level(1, 16, 30);
        Chicken chicken = map.getChicken();
        chicken.removeLife();
        assertEquals(2, map.getLives());

    }

    @Test
    public void isLevelFinished(){
        Level map = new Level(1, 35, 40);
        Chicken chicken = map.getChicken();

        assertFalse(map.isLevelFinished());

        chicken.setPosition(new Position(0, 0));

        assertTrue(map.isLevelFinished());

        chicken.setPosition(new Position(10, 10));
        while(chicken.getLives()>0) chicken.removeLife();

        assertTrue(map.isChickenDead());
    }*/
}
