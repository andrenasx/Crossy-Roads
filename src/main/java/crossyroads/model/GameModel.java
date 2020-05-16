package crossyroads.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private int width;
    private int height;
    private int currentLevel;
    private int maxLevels;
    private List<GameMap> levels;

    public GameModel(int width, int height, int maxLevels) {
        this.width = width;
        this.height = height;
        this.currentLevel = 0;
        this.maxLevels = maxLevels;
        this.levels = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void createLevels(){
        for(int i=1; i<=maxLevels; i++){
            GameMap level = new GameMap(i, width, height);
            for (Element element: level.getLevelTerrain().getElements()){
                level.addElement(element);
            }
            levels.add(level);
        }
    }

    public List<GameMap> getLevels() {
        return levels;
    }

    public GameMap getCurrentLevel(){
        return levels.get(currentLevel);
    }

    public void setCurrentLevel(int level){
        this.currentLevel = level;
    }

    public void increaseLevel(){
        this.currentLevel++;
    }
}
