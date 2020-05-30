package crossyroads.model;

import java.io.IOException;

public class LevelCreator {

    public LevelCreator() {
    }

    public Level createLevel(int width, int height, int lvl) throws IOException {
        Terrain terrain = new Terrain("level" + lvl + ".txt", "main");
        Level level = new Level(width, height, terrain);
        for (Element element: terrain.getElements()){
            level.addElement(element);
        }

        return level;
    }
}
