package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TruckTest {
    @Test
    public void truckColorTest(){
        Truck truck = new Truck(1, 1, "right");
        assertEquals("#0000FF", truck.getColor());
    }

    @Test
    public void truckLengthTest(){
        Truck truck = new Truck(1, 1, "right");
        assertEquals(4, truck.getLength());
    }

    @Test
    public void truckGetPositionTest(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Truck truck = new Truck(x, y, "right");
        assertEquals(x, truck.getPosition().getX());
        assertEquals(y, truck.getPosition().getY());
    }

    @Test
    public void truckSetPositionTest(){
        Truck truck = new Truck(1,1,"left");
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        truck.setPosition(new Position(x, y));
        assertEquals(x, truck.getPosition().getX());
        assertEquals(y, truck.getPosition().getY());

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
        Truck truck = new Truck(13, 15, "right");
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
