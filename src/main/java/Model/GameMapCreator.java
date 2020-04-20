package Model;

public class GameMapCreator {
    public GameMapCreator() {
    }

    public GameMap createGameMap(int width, int height) {
        GameMap gameMap = new GameMap(width, height);
        Car car = new Car(0, 10, "right");
        Car car2 = new Car(2, 23, "right");
        gameMap.addElement(car);
        gameMap.addElement(car2);
        Truck truck = new Truck(20, 11, "left");
        Truck truck2 = new Truck(5, 25, "right");
        gameMap.addElement(truck);
        gameMap.addElement(truck2);
        Coin coin = new Coin(30,5,1);
        Coin coin2 = new Coin(20, 15, 3);
        gameMap.addElement(coin);
        gameMap.addElement(coin2);
        return gameMap;
    }


}
