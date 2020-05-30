package crossyroads.model;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TerrainTest {
    @Test
    public void readFileTest() throws IOException {
        Terrain terrain = new Terrain("test.txt", "test");
        assertEquals("Test", terrain.getBackground());
    }

    /*@Test
    public void getElementsTest(){
        Terrain terrain = new Terrain("test.txt", "test");

        List<Element> e = new ArrayList<>();
        e.add(new Car(0,10,"right"));
        e.add(new Car(2,23,"left"));
        e.add(new Truck(20,11,"left"));
        e.add(new Truck(5,25,"right"));
        e.add(new Coin(30,5,1));

        assertEquals(e,terrain.getElements());
    }*/
}
