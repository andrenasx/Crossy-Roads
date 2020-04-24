package Model;

public class Truck extends Vehicle {
    public Truck(int x, int y, String direction) {
        super(x, y, direction);
        this.length=4;
        setCharacter("T");
        setColor("#0000FF");
    }
}
