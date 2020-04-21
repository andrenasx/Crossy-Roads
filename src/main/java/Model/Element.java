package Model;

public abstract class Element {
    private Position position;
    protected String character;
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

    public String getCharacter() {
        return character;
    }

    protected void setCharacter(String character) {
        this.character = character;
    }

    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }
}