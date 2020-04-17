package Model;

public class Chicken extends Element{
    private int score;
    private int lives;

    public Chicken(int x, int y) {
        super(x, y);
        this.lives = 3;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead(){
        return this.lives<=0;
    }
}