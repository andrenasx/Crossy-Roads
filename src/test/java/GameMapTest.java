import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameMapTest {
    @Test
    public void getGameMapSizeTest(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);

        GameMap game = new GameMap(x,y);
        assertEquals(x,game.getWidth());
        assertEquals(y,game.getHeight());
    }

    @Test
    public void addElementTest(){
        GameMap game = new GameMap(30,70);

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
        GameMap game = new GameMap(30,70);

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
    public void moveChickenTest(){
        GameMap game = new GameMap(30,70);
        Position position = new Position(15,15);
        game.moveChicken(position);

        assertEquals(position,game.getChickenPosition());

    }

}
