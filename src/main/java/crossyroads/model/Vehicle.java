package crossyroads.model;

public abstract class Vehicle extends Element {
    private String direction;

    public Vehicle(int x, int y, String direction) {
        super(x, y);
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public abstract int getLength();

    public abstract int getSpeed();

    public boolean checkCollision(Position position){
        return (this.getPosition().getX()<=position.getX() && (this.getPosition().getX()+this.getLength()-1)>=position.getX() && this.getPosition().getY()==position.getY());
    }
}
