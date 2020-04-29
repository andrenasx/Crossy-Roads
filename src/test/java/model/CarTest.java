package model;

import model.Car;
import model.Position;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

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
    public void carGetPosition(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Car car = new Car(x, y, "right");
        assertEquals(x, car.getPosition().getX());
        assertEquals(y, car.getPosition().getY());
    }

    @Test
    public void carSetPosition(){
        Car car = new Car(1,1,"left");
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        car.setPosition(new Position(x, y));
        assertEquals(x, car.getPosition().getX());
        assertEquals(y, car.getPosition().getY());

    }
}
