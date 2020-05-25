package crossyroads.model;

public class LevelCreator {

    public LevelCreator() {
    }

    public Level createLevel(int width, int height, int lvl){
        Terrain terrain = new Terrain("level" + lvl + ".txt", "main");
        Level level = new Level(lvl, width, height, terrain);
        for (Element element: terrain.getElements()){
            level.addElement(element);
        }

        return level;
    }
}
