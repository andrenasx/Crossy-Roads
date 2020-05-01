package crossyroads.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TerrainTest {
    @Test
    public void readFileTest(){
        Terrain terrain = new Terrain("test.txt", "test");
        assertEquals("Test", terrain.getTerrainStrings());
    }
}
