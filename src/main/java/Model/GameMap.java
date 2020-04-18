package Model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Chicken chicken;
    private List<Vehicle> vehicles;
    private boolean gamefinished;
    private List<GameMapObserver> observers;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.chicken = new Chicken(15, 20);
        this.vehicles = new ArrayList<>();
        this.gamefinished = false;
        this.observers = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addElement(Element element) {
        if (element instanceof Chicken) chicken = (Chicken) element;
        if (element instanceof Car) vehicles.add((Car) element);
        if (element instanceof Truck) vehicles.add((Truck) element);

    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.add(chicken);
        elements.addAll(vehicles);
        return elements;
    }

    public Position getChickenPosition(){
        return chicken.getPosition();
    }

    private boolean chickenStaysInScreen(Position position){
        if (position.getX() < 0 || position.getX() >= width)
            return false;
        if (position.getY() < 0 || position.getY() >= height)
            return false;
        return true;
    }

    public void moveChicken(Position position){
        if (chickenStaysInScreen(position))
            chicken.setPosition(position);
        this.notifyObservers();
    }

    public void moveVehicles(){
        for (Vehicle vehicle: vehicles){
            vehicle.setPosition(vehicle.getPosition().left());
        }
        this.notifyObservers();
    }

    public boolean isGameFinished(){
        return gamefinished || chicken.isDead();
    }

    public void gameFinish(){
        this.gamefinished = true;
    }

    public void addObserver(GameMapObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (GameMapObserver observer : observers) {
            observer.gameMapChanged();
        }
    }
}
