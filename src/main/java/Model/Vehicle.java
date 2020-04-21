package Model;

public abstract class Vehicle extends Element {
    private String direction;
    protected int lenght;

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

    public int getLenght() {
        return lenght;
    }

    @Override
    public String getCharacter() {
        String chr="";
        for(int i=1; i<=lenght;i++){
            chr = chr.concat(character);
        }

        return chr;
    }
}
