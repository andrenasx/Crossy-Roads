package crossyroads.model;

public class Score {
    private int level;
    private int coins;
    private int steps;

    public Score(){}

    public Score(int level, int coins, int steps){
        this.level = level;
        this.coins = coins;
        this.steps = steps;
    }

    public int getLevel() {
        return level;
    }

    public int getCoins() {
        return coins;
    }

    public int getSteps() {
        return steps;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

}

