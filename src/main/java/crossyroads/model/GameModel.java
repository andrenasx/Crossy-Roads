package crossyroads.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private int width;
    private int height;
    private int currentLevel;
    private List<Level> levels;
    private Chicken chicken;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentLevel = 1;
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

    public void addLevel(Level level){
        levels.add(level);
    }

    public Level getCurrentLevel(){
        return levels.get(currentLevel-1);
    }

    public void increaseLevel(){
        if(!this.isFinalLevel()) this.currentLevel++;
    }

    public boolean isFinalLevel(){
        return this.currentLevel == levels.size();
    }

    public int getCurrentLevelInt(){
        return currentLevel;
    }
}
