package crossyroads.model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TerrainTest {
    @Test
    public void readFileTest() throws IOException {
        Terrain terrain = new Terrain("test.txt", "test");
        assertEquals("Test", terrain.getBackground());
    }
}
