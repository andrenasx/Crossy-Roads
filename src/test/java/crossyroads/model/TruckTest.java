package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TruckTest {
    Random rand;
    int x, y;
    Truck truck;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(40);
        y = rand.nextInt(35);

        truck = new Truck(x, y, "right");
    }

    @Test
    public void truckColorTest(){
        assertEquals("#0000FF", truck.getColor());
    }

    @Test
    public void truckLengthTest(){
        assertEquals(4, truck.getLength());
    }

    @Test
    public void truckGetSpeedTest(){
        assertEquals(6, truck.getSpeed());
    }

    @Test
    public void truckGetPositionTest(){
        assertEquals(x, truck.getPosition().getX());
        assertEquals(y, truck.getPosition().getY());
    }

    @Test
    public void truckSetPositionTest(){
        int new_x = rand.nextInt(40);
        int new_y = rand.nextInt(35);
        truck.setPosition(new Position(new_x, new_y));
        assertEquals(new_x, truck.getPosition().getX());
        assertEquals(new_y, truck.getPosition().getY());

    }

    @Test
    public void truckGetDirectionTest(){
        Truck truck1 = new Truck(1, 2, "right");
        assertEquals("right", truck1.getDirection());
        Truck truck2 = new Truck(3, 3, "left");
        assertEquals("left", truck2.getDirection());
    }

    @Test
    public void truckCheckCollisionTest(){
        truck.setPosition(new Position(13, 15));
        Position position1 = new Position(13, 18);
        assertFalse(truck.checkCollision(position1));
        Position position2 = new Position(15, 15);
        assertTrue(truck.checkCollision(position2));
        Position position3 = new Position(10, 15);
        assertFalse(truck.checkCollision(position3));
        Position position4 = new Position(18, 15);
        assertFalse(truck.checkCollision(position4));
    }
}
