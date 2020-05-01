package crossyroads.model;

public class Truck extends Vehicle {
    public Truck(int x, int y, String direction) {
        super(x, y, direction);
    }

    @Override
    public int getLength() {
        return 4;
    }

    @Override
    public String getColor() {
        return "#0000FF";
    }
}
