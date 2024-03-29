package crossyroads.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int width;
    private int height;
    private List<Vehicle> vehicles;
    private List<Coin> coins;
    private Terrain terrain;

    public Level(int width, int height, Terrain terrain) {
        this.width = width;
        this.height = height;
        this.terrain = terrain;
        this.vehicles = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Coin> getCoins(){ return coins; }

    public List<Vehicle> getVehicles(){return vehicles;}

    public void addElement(Element element) {
        if (element instanceof Car) vehicles.add((Car) element);
        if (element instanceof Truck) vehicles.add((Truck) element);
        if (element instanceof Coin) coins.add((Coin) element);

    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.addAll(coins);
        elements.addAll(vehicles);
        return elements;
    }

    public Terrain getLevelTerrain(){
        return terrain;
    }
}
