package Model;

public class GameMapCreator {
    public GameMapCreator() {
    }

    public GameMap createGameMap(int width, int height) {
        GameMap gameMap = new GameMap(width, height);
        Car car = new Car(15, 30, "right");
        gameMap.addElement(car);
        Truck truck = new Truck(10, 20, "left");
        gameMap.addElement(truck);
        return gameMap;
    }


}
