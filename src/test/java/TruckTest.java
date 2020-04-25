import model.Truck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TruckTest {
    @Test
    public void TruckColorTest(){
        Truck truck = new Truck(1, 1, "right");
        assertEquals("#0000FF", truck.getColor());
    }

    @Test
    public void CarLengthTest(){
        Truck truck = new Truck(1, 1, "right");
        assertEquals(4, truck.getLength());
    }
}
