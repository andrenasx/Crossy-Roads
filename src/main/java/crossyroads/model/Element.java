package crossyroads.model;

public abstract class Element {
    private Position position;
    protected String color;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getColor();
}