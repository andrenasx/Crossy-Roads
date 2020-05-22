package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GameModelTest {
    @Test
    public void GameModelGetSize(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);
        int max = rand.nextInt(10);
        GameModel gameModel = new GameModel(x, y, max);
        assertEquals(x, gameModel.getWidth());
        assertEquals(y, gameModel.getHeight());
    }

    @Test
    public void resetChickenTest(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);
        int max = rand.nextInt(10);
        GameModel gameModel = new GameModel(x, y, max);
        Chicken chicken = gameModel.getChicken();
        chicken.setPosition(new Position(1, 2));
        gameModel.resetChickenPosition();
        assertEquals(chicken.getPosition(), new Position(x/2, y-1));
    }

    @Test
    public void getScoreTest(){
        GameModel gameModel = new GameModel(35, 40, 5);
        Chicken chicken = gameModel.getChicken();
        chicken.raiseScore(3);
        assertEquals(3, gameModel.getScore());
    }

    @Test
    public void getLivesTest(){
        GameModel gameModel = new GameModel(35, 40, 5);
        Chicken chicken = gameModel.getChicken();
        chicken.removeLife();
        assertEquals(2, gameModel.getLives());
    }

    @Test
    public void isLevelFinishedTest(){
        //TODO acrescentar a condição do vetor as moedas estar vazio
        GameModel gameModel = new GameModel(35, 40, 5);
        Chicken chicken = gameModel.getChicken();
        assertFalse(gameModel.isLevelFinished());
        while(chicken.getLives()>0) chicken.removeLife();
        assertTrue(gameModel.isChickenDead());
    }

    @Test
    public void getCurrentLevelIntTest(){
        GameModel gameModel = new GameModel(35, 40, 5);
        assertEquals(1, gameModel.getCurrentLevelInt());
    }

    @Test
    public void increaseLevelTest(){
        GameModel gameModel = new GameModel(35, 40, 2);
        int exp1 = gameModel.getCurrentLevelInt();
        gameModel.increaseLevel();
        assertEquals(exp1 + 1, gameModel.getCurrentLevelInt());
        int exp2 = gameModel.getCurrentLevelInt();
        assertEquals(exp2, gameModel.getCurrentLevelInt());
    }

    @Test
    public void isFinalLevelTest(){
        GameModel gameModel = new GameModel(35, 40, 5);
        assertFalse(gameModel.isFinalLevel());
        while(gameModel.getCurrentLevelInt() < 5) gameModel.increaseLevel();
        assertTrue(gameModel.isFinalLevel());
    }
}
