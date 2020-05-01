package crossyroads.model;

import org.junit.Test;

import java.util.Random;

 import static org.junit.Assert.*;

 public class ChickenTest {
    @Test
    public void chickenGetPositionTest(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Chicken chicken = new Chicken(x, y);
        assertEquals(x, chicken.getPosition().getX());
        assertEquals(y, chicken.getPosition().getY());
    }

    @Test
    public void chickenScoreTest(){
        Random rand = new Random();
        int quantity = rand.nextInt(10);
        Chicken chicken = new Chicken(1, 2);
        int score = chicken.getScore();
        chicken.raiseScore(quantity);
        assertEquals(score + quantity, chicken.getScore());

    }

    @Test
    public void chickenLivesTest(){
        Chicken chicken = new Chicken(1, 2);
        int lives = chicken.getLives();
        chicken.removeLife();
        assertEquals(lives-1, chicken.getLives());
        assertFalse(chicken.isDead());
        chicken.removeLife();
        chicken.removeLife();
        assertTrue(chicken.isDead());
    }

     @Test
     public void chickenColorTest(){
         Chicken chicken = new Chicken(1, 2);
         assertEquals("#FFFFFF", chicken.getColor());
     }

     @Test
     public void chickenSetPositionTest(){
        Chicken chicken = new Chicken(1, 2);
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        chicken.setPosition(new Position(x, y));
        assertEquals(x, chicken.getPosition().getX());
        assertEquals(y, chicken.getPosition().getY());

     }
}
