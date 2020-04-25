import model.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
    @Test
    public void CarColorTest(){
        Car car = new Car(1, 1, "left");
        assertEquals("#FF0000", car.getColor());
    }

    @Test
    public void CarLengthTest(){
        Car car = new Car(1, 1, "right");
        assertEquals(2, car.getLength());
    }
}
