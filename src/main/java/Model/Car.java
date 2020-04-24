package Model;

public class Car extends Vehicle {
    public Car(int x, int y, String direction) {
        super(x, y, direction);
        setCharacter("C");
        setColor("#FF0000");
    }

    @Override
    public int getLength() {
        return 2;
    }
}
