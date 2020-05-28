package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GameModelTest {
    @Test
    public void GameModelGetSize(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);
        GameModel gameModel = new GameModel(x, y);
        assertEquals(x, gameModel.getWidth());
        assertEquals(y, gameModel.getHeight());
    }

    @Test
    public void resetChickenTest(){
        Random rand = new Random();
        int x = rand.nextInt(50);
        int y = rand.nextInt(70);
        GameModel gameModel = new GameModel(x, y);
        Chicken chicken = gameModel.getChicken();
        chicken.setPosition(new Position(1, 2));
        gameModel.resetChickenPosition();
        assertEquals(chicken.getPosition(), new Position(x/2, y-1));
    }

    @Test
    public void getScoreTest(){
        GameModel gameModel = new GameModel(35, 40);
        Chicken chicken = gameModel.getChicken();
        chicken.raiseScore(3);
        assertEquals(3, gameModel.getScore());
    }

    @Test
    public void getLivesTest(){
        GameModel gameModel = new GameModel(35, 40);
        Chicken chicken = gameModel.getChicken();
        chicken.removeLife();
        assertEquals(2, gameModel.getLives());
    }

    @Test
    public void isLevelFinishedTest(){
        //TODO acrescentar a condição do vetor as moedas estar vazio
        GameModel gameModel = new GameModel(35, 40);
        Chicken chicken = gameModel.getChicken();
        assertFalse(gameModel.isLevelFinished());
        while(chicken.getLives()>0) chicken.removeLife();
        assertTrue(gameModel.isChickenDead());
    }

    @Test
    public void getCurrentLevelIntTest(){
        GameModel gameModel = new GameModel(35, 40);
        Level level1 = mock(Level.class);
        Level level2 = mock(Level.class);
        gameModel.addLevel(level1);
        gameModel.addLevel(level2);
        assertEquals(1, gameModel.getCurrentLevelInt());
        gameModel.increaseLevel();
        assertEquals(2, gameModel.getCurrentLevelInt());
    }

    @Test
    public void isFinalLevelTest(){
        GameModel gameModel = new GameModel(35, 40);
        Level level1 = mock(Level.class);
        Level level2 = mock(Level.class);
        gameModel.addLevel(level1);
        gameModel.addLevel(level2);
        assertFalse(gameModel.isFinalLevel());
        gameModel.increaseLevel();
        assertTrue(gameModel.isFinalLevel());
    }

    @Test
    public void getCurrentLevelTest(){
        GameModel gameModel = new GameModel(35, 40);
        Level level1 = mock(Level.class);
        Level level2 = mock(Level.class);
        gameModel.addLevel(level1);
        gameModel.addLevel(level2);
        assertEquals(level1, gameModel.getCurrentLevel());
    }
}
