package model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Chicken chicken;
    private List<Vehicle> vehicles;
    private boolean gamefinished;
    private List<GameMapObserver> observers;
    private List<Coin> coins;
    private Terrain terrain;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.chicken = new Chicken(width/2, height-1);
        this.vehicles = new ArrayList<>();
        this.gamefinished = false;
        this.observers = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.terrain = new Terrain("map.txt");
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

    public List<Coin> getCoins(){ return coins; }

    public Chicken getChicken(){ return chicken;}

    public void addElement(Element element) {
        if (element instanceof Chicken) chicken = (Chicken) element;
        if (element instanceof Car) vehicles.add((Car) element);
        if (element instanceof Truck) vehicles.add((Truck) element);
        if (element instanceof Coin) coins.add((Coin) element);

    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.add(chicken);
        elements.addAll(vehicles);
        elements.addAll(coins);
        return elements;
    }

    public Position getChickenPosition(){
        return chicken.getPosition();
    }

    private boolean chickenStaysInScreen(Position position){
        return (position.getX()>=0 && position.getX()<width && position.getY()>=0 && position.getY()<height);
    }

    public void moveChicken(Position position){
        if (chickenStaysInScreen(position))
            chicken.setPosition(position);
        checkCollisions(position);

        this.notifyObservers();
    }

    public void moveVehicles(){
        for (Vehicle vehicle: vehicles){
            if(vehicle.getDirection().equals("left"))
                vehicle.setPosition(vehicle.getPosition().left());
            else
                vehicle.setPosition(vehicle.getPosition().right());
        }
        this.notifyObservers();
    }

    private void checkCollisions(Position position) {
        checkVehicleCollision(position);

        Coin coin = (Coin) getCollidingElement(position, coins);
        if (coin != null) {
            chicken.raiseScore(coin.getValue());
            coins.remove(coin);
        }
    }

    private void checkVehicleCollision(Position position){
        for (Vehicle vehicle: vehicles){
            if (vehicle.checkCollision(position)){
                chicken.removeLife();
                break;
            }
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }

    public boolean isGameFinished(){
        return this.gamefinished || chicken.isDead() || coins.isEmpty();
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

    public int getScore() {
        return chicken.getScore();
    }

    public int getLives() {
        return chicken.getLives();
    }

    public String getGameTerrain(){
        return terrain.getTerrainStrings();
    }
}