package crossyroads.model;

public class Chicken extends Element{
    private int score;
    private int lives;
    private int count_steps;

    public Chicken(int x, int y) {
        super(x, y);
        this.lives = 3;
        this.score = 0;
        this.count_steps = 0;
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

    public int getCountSteps(){
        return count_steps;
    }

    public void increaseCount(){
        count_steps++;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
