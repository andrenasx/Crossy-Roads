package model;

import model.Car;
import model.Position;
import model.Truck;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

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
    public void truckGetPosition(){
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);

        Truck truck = new Truck(x, y, "right");
        assertEquals(x, truck.getPosition().getX());
        assertEquals(y, truck.getPosition().getY());
    }

    @Test
    public void truckSetPosition(){
        Truck truck = new Truck(1,1,"left");
        Random rand = new Random();
        int x = rand.nextInt(40);
        int y = rand.nextInt(35);
        truck.setPosition(new Position(x, y));
        assertEquals(x, truck.getPosition().getX());
        assertEquals(y, truck.getPosition().getY());

    }
}
