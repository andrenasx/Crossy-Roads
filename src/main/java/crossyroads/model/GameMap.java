package crossyroads.model;

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
        this.terrain = new Terrain("map.txt", "main");
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

    public List<Vehicle> getVehicles(){return vehicles;}

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

    public void resetChickenPosition(){
        chicken.setPosition(new Position(width/2, height-1));
    }
}
