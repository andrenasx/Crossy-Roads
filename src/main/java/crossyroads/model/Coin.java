package crossyroads.model;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public String getColor() {
        return "#FFFF00";
    }
}
