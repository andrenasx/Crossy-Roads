package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;

public class PositionTest {
    Random rand;
    int x, y;
    Position position;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(40);
        y = rand.nextInt(35);

        position = new Position(x, y);
    }

    @Test
    public void getPosition(){
        assertEquals(x, position.getX());
        assertEquals(y, position.getY());
    }

    @Test
    public void positionChange(){
        Position positionLeft = new Position(x-1, y);
        Position positionRight = new Position(x+1,y);
        Position positionUp = new Position(x,y-1);
        Position positionDown = new Position(x,y+1);

        assertEquals(positionLeft, position.left());
        assertEquals(positionRight, position.right());
        assertEquals(positionUp, position.up());
        assertEquals(positionDown, position.down());
    }

    @Test
    public void positionHash(){
        Position position2 = new Position(x, y);
        Position position3 = new Position(100, 100);

        assertEquals(position, position2);
        assertNotEquals(position, position3);
    }
}
