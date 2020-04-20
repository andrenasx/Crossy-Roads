import Model.Position;
import javafx.geometry.Pos;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void getPosition(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Position position = new Position(x, y);
        assertEquals(x, position.getX());
        assertEquals(y, position.getY());
    }

    @Test
    public void positionChange(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Position position = new Position(x, y);
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
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Position position1 = new Position(x, y);
        Position position2 = new Position(x, y);
        Position position3 = new Position(100, 100);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }
}
