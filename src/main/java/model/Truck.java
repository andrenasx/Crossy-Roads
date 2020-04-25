package model;

public class Truck extends Vehicle {
    public Truck(int x, int y, String direction) {
        super(x, y, direction);
        setCharacter("T");
        setColor("#0000FF");
    }

    @Override
    public int getLength() {
        return 4;
    }
}
