package crossyroads.model;

import java.io.IOException;

public class GameModelCreator {

    public GameModelCreator() {
    }

    public GameModel createGameModel(int width, int height){
        GameModel gameModel = new GameModel(width, height);

        boolean done = false;
        int i=1;
        while (!done){
            try{
                LevelCreator lc = new LevelCreator();
                Level level = lc.createLevel(width, height, i);
                gameModel.addLevel(level);
                i++;
            }
            catch (IOException e){
                done = true;
            }
        }

        return gameModel;
    }
}
