package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CarTest {
    @Test
    public void carColorTest(){
        Car car = new Car(1, 1, "left");
        assertEquals("#FF0000", car.getColor());
    }

    @Test
    public void carLengthTest(){
        Car car = new Car(1, 1, "right");
        assertEquals(2, car.getLength());
    }

    @Test
    public void carGetPositionTest(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Car car = new Car(x, y, "right");
        assertEquals(x, car.getPosition().getX());
        assertEquals(y, car.getPosition().getY());
    }

    @Test
    public void carSetPositionTest(){
        Car car = new Car(1,1,"left");
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        car.setPosition(new Position(x, y));
        assertEquals(x, car.getPosition().getX());
        assertEquals(y, car.getPosition().getY());

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
        Car car = new Car(12, 15, "right");
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
