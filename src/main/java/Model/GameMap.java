package Model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int height;
    private int width;

    private Chicken chicken;

    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.chicken = new Chicken(20, 40);
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

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.add(chicken);

        return elements;
    }
}
