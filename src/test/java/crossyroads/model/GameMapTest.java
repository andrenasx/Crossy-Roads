package crossyroads.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class GameMapTest {
    @Test
    public void getGameMapSizeTest(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);

        GameMap game = new GameMap(1, x,y);
        assertEquals(x,game.getWidth());
        assertEquals(y,game.getHeight());
    }

    @Test
    public void addElementTest(){
        GameMap game = new GameMap(1, 30,70);

        Chicken chicken = new Chicken(15,65);
        //Test for vehicles
        Coin coin = new Coin(10,10,1);
        Coin coin1 = new Coin(20,20,1);
        Coin coin2 = new Coin(25,20,1);

        game.addElement(chicken);
        //Test for vehicles
        game.addElement(coin);
        game.addElement(coin1);
        game.addElement(coin2);

        List<Coin> coins = new ArrayList<>();
        coins.add(coin);
        coins.add(coin1);
        coins.add(coin2);

        assertEquals(coins,game.getCoins());
        assertEquals(chicken, game.getChicken());

    }

    @Test
    public void getAllElementsTest(){
        GameMap game = new GameMap(1, 30,70);

        Chicken chicken = new Chicken(15,65);
        Coin coin = new Coin(10,10,1);
        Coin coin1 = new Coin(20,20,1);

        game.addElement(chicken);
        //Test for vehicles
        game.addElement(coin);
        game.addElement(coin1);

        List<Element> elements = new ArrayList<>();
        elements.add(chicken);
        //Test for vehicles
        elements.add(coin);
        elements.add(coin1);

        assertEquals(elements,game.getAllElements());

    }

    @Test
    public void resetChickenTest(){
        GameMap map = new GameMap(1, 14, 40);
        Chicken chicken = map.getChicken();
        chicken.setPosition(new Position(1, 2));
        map.resetChickenPosition();
        assertEquals(chicken.getPosition(), new Position(14/2, 40-1));
    }

    @Test
    public void getScoreTest(){
        GameMap map = new GameMap(1, 15, 30);
        Chicken chicken = map.getChicken();
        chicken.raiseScore(3);
        assertEquals(3, map.getScore());
    }

    @Test
    public void getLivesTest(){
        GameMap map = new GameMap(1, 16, 30);
        Chicken chicken = map.getChicken();
        chicken.removeLife();
        assertEquals(2, map.getLives());

    }

    @Test
    public void isLevelFinished(){
        GameMap map = new GameMap(1, 35, 40);
        Chicken chicken = map.getChicken();

        assertFalse(map.isLevelFinished());

        chicken.setPosition(new Position(0, 0));

        assertTrue(map.isLevelFinished());

        chicken.setPosition(new Position(10, 10));
        while(chicken.getLives()>0) chicken.removeLife();

        assertTrue(map.isChickenDead());
    }

}
