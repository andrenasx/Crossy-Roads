package crossyroads.model;

public class GameMapCreator {
    public GameMapCreator() {
    }

    public GameMap createGameMap(int width, int height) {
        GameMap gameMap = new GameMap(width, height);
        for (Element element: gameMap.getLevelTerrain().getElements()){
            gameMap.addElement(element);
        }

        return gameMap;
    }
}
