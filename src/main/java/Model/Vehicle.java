package Model;

public abstract class Vehicle extends Element {
    private String direction;
    protected int length;

    public Vehicle(int x, int y, String direction) {
        super(x, y);
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String getCharacter() {
        String chr="";
        for(int i=1; i<=length;i++){
            chr = chr.concat(character);
        }

        return chr;
    }
}
