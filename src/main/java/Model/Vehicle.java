package Model;

public abstract class Vehicle extends Element {
    private String direction;

    public Vehicle(int x, int y, String direction) {
        super(x, y);
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
