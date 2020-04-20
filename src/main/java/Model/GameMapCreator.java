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
        Coin coin = new Coin(30,30,1);
        Coin coin2 = new Coin(20, 15, 3);
        gameMap.addElement(coin);
        gameMap.addElement(coin2);
        return gameMap;
    }


}
