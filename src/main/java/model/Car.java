package model;

public class Car extends Vehicle {
    public Car(int x, int y, String direction) {
        super(x, y, direction);
    }

    @Override
    public int getLength() {
        return 2;
    }

    @Override
    public String getColor() {
        return "#FF0000";
    }
}
