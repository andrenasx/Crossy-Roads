package Model;

public class GameMapCreator {
    public GameMapCreator() {
    }

    public GameMap createGameMap(int width, int height) {
        GameMap gameMap = new GameMap(width, height);
        Car car = new Car(15, 40, "right");
        gameMap.addElement(car);
        return gameMap;
    }


}
