package crossyroads.model;

public class GameModelCreator {

    public GameModelCreator() {
    }

    public GameModel createGameModel(int width, int height, int maxLevels){
        GameModel gameModel = new GameModel(width, height);

        for(int i=1; i<=maxLevels; i++){
            LevelCreator lc = new LevelCreator();
            Level level = lc.createLevel(width, height, i);
            gameModel.addLevel(level);
        }

        return gameModel;
    }
}
