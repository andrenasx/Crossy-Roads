package Model;

public class Coin extends Element {
    private int value;
    public Coin(int x, int y, int value) {
        super(x, y);
        this.value = value;
        setCharacter("S");
        setColor("#FFFF00");
    }

    public int getValue() {
        return value;
    }
}
