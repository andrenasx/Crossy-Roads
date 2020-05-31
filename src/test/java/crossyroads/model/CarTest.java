package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CarTest {
    Car car;
    Random rand;
    int x, y;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(40);
        y = rand.nextInt(35);
        car = new Car(x, y, "right");
    }

    @Test
    public void carColorTest(){
        assertEquals("#FF0000", car.getColor());
    }

    @Test
    public void carLengthTest(){
        assertEquals(2, car.getLength());
    }

    @Test
    public void carGetSpeedTest(){
        assertEquals(3, car.getSpeed());
    }

    @Test
    public void carGetPositionTest(){
        assertEquals(x, car.getPosition().getX());
        assertEquals(y, car.getPosition().getY());
    }

    @Test
    public void carSetPositionTest(){
        int new_x = rand.nextInt(40);
        int new_y = rand.nextInt(35);
        car.setPosition(new Position(new_x, new_y));
        assertEquals(new_x, car.getPosition().getX());
        assertEquals(new_y, car.getPosition().getY());

    }

    @Test
    public void carGetDirectionTest(){
        Car car1 = new Car(1, 1, "left");
        assertEquals("left", car1.getDirection());
        Car car2 = new Car(2, 2, "right");
        assertEquals("right", car2.getDirection());
    }

    @Test
    public void carCheckCollisionTest(){
        car.setPosition(new Position(12, 15));
        Position position1 = new Position(18, 15);
        assertFalse(car.checkCollision(position1));
        Position position2 = new Position(15, 3);
        assertFalse(car.checkCollision(position2));
        Position position3 = new Position(10, 15);
        assertFalse(car.checkCollision(position3));
        Position position4 = new Position(13, 15);
        assertTrue(car.checkCollision(position4));
    }
}
