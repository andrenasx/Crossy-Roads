package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

 import static org.junit.Assert.*;

 public class ChickenTest {
     Random rand;
     int x, y;
     Chicken chicken;

     @Before
     public void init() {
         rand = new Random();
         x = rand.nextInt(40);
         y = rand.nextInt(35);

         chicken = new Chicken(x, y);
     }

    @Test
    public void chickenGetPositionTest(){
        assertEquals(x, chicken.getPosition().getX());
        assertEquals(y, chicken.getPosition().getY());
    }

    @Test
    public void chickenScoreTest(){
        int quantity = rand.nextInt(10);
        int score = chicken.getScore();
        chicken.raiseScore(quantity);
        assertEquals(score + quantity, chicken.getScore());

    }

    @Test
    public void chickenLivesTest(){
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
         assertEquals("#FFFFFF", chicken.getColor());
     }

     @Test
     public void chickenSetPositionTest(){
        int new_x = rand.nextInt(40);
        int new_y = rand.nextInt(35);
        chicken.setPosition(new Position(new_x, new_y));
        assertEquals(new_x, chicken.getPosition().getX());
        assertEquals(new_y, chicken.getPosition().getY());

     }

     @Test
     public void chickenStepsTest(){
        assertEquals(0, chicken.getCountSteps());
        chicken.increaseCount();
        assertEquals(1, chicken.getCountSteps());
     }
}
