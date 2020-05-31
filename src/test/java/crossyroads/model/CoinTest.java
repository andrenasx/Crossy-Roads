package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CoinTest {
    Random rand;
    int x, y;
    Coin coin;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(40);
        y = rand.nextInt(35);

        coin = new Coin(x, y);
    }

    @Test
    public void coinGetPositionTest(){
        assertEquals(x, coin.getPosition().getX());
        assertEquals(y, coin.getPosition().getY());
    }

    @Test
    public void coinColorTest(){
        assertEquals("#FFFF00", coin.getColor());
    }

     @Test
    public void coinSetPosition(){
        int new_x = rand.nextInt(40);
        int new_y = rand.nextInt(35);
        coin.setPosition(new Position(new_x, new_y));
        assertEquals(new_x, coin.getPosition().getX());
        assertEquals(new_y, coin.getPosition().getY());
     }
}
