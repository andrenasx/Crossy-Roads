package crossyroads.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private int width;
    private int height;
    private int currentLevel;
    private int maxLevels;
    private List<Level> levels;
    private Chicken chicken;

    public GameModel(int width, int height, int maxLevels) {
        this.width = width;
        this.height = height;
        this.currentLevel = 1;
        this.maxLevels = maxLevels;
        this.levels = new ArrayList<>();
        this.chicken = new Chicken(width/2, height-1);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Chicken getChicken(){ return chicken;}

    public boolean isChickenDead() {
        return chicken.isDead();
    }

    public boolean isLevelFinished(){
        return chicken.getPosition().getY() == 0 && getCurrentLevel().getCoins().isEmpty();
    }

    public int getScore() {
        return chicken.getScore();
    }

    public int getLives() {
        return chicken.getLives();
    }

    public void resetChickenPosition(){
        chicken.setPosition(new Position(width/2, height-1));
    }

    public void createLevels(){
        for(int i=1; i<=maxLevels; i++){
            Level level = new Level(i, width, height);
            for (Element element: level.getLevelTerrain().getElements()){
                level.addElement(element);
            }
            levels.add(level);
        }
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Level getCurrentLevel(){
        return levels.get(currentLevel-1);
    }

    public void setCurrentLevel(int level){
        this.currentLevel = level;
    }

    public void increaseLevel(){
        if(!this.isFinalLevel()) this.currentLevel++;
    }

    public boolean isFinalLevel(){
        return maxLevels == this.currentLevel;
    }

    public int getCurrentLevelInt(){
        return currentLevel;
    }
}
