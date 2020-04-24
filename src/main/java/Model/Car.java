package Model;

public class Car extends Vehicle {
    public Car(int x, int y, String direction) {
        super(x, y, direction);
        this.length=2;
        setCharacter("C");
        setColor("#FF0000");
    }
}
