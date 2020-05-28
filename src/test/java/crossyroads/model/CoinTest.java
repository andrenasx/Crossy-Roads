package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CoinTest {
    @Test
    public void coinGetPositionTest(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Coin coin = new Coin(x, y);
        assertEquals(x, coin.getPosition().getX());
        assertEquals(y, coin.getPosition().getY());
    }

    @Test
    public void coinColorTest(){
        Coin coin = new Coin(1,1);
        assertEquals("#FFFF00", coin.getColor());
    }

     @Test
    public void coinSetPosition(){
        Coin coin = new Coin(1, 2);
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        coin.setPosition(new Position(x, y));
        assertEquals(x, coin.getPosition().getX());
        assertEquals(y, coin.getPosition().getY());
     }
}
