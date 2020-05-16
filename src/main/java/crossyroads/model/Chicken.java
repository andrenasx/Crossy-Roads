package crossyroads.model;

public class Chicken extends Element{
    private int score;
    private int lives;

    public Chicken(int x, int y) {
        super(x, y);
        this.lives = 3;
        this.score = 0;
    }

    @Override
    public String getColor() {
        return "#FFFFFF";
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

    public void raiseScore(int quantity){
        this.score += quantity;
    }

    public void removeLife(){this.lives-=1;}

    public void setScore(int score) {
        this.score = score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
