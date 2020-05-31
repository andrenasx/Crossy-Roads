package crossyroads.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GameModelTest {
    Random rand;
    int x, y;
    GameModel gameModel;
    Level level1, level2;

    @Before
    public void init(){
        rand = new Random();
        x = rand.nextInt(50);
        y = rand.nextInt(70);
        gameModel = new GameModel(x, y);

        level1 = mock(Level.class);
        level2 = mock(Level.class);
        gameModel.addLevel(level1);
        gameModel.addLevel(level2);
    }

    @Test
    public void GameModelGetSize(){
        assertEquals(x, gameModel.getWidth());
        assertEquals(y, gameModel.getHeight());
    }

    @Test
    public void resetChickenTest(){
        Chicken chicken = gameModel.getChicken();
        chicken.setPosition(new Position(1, 2));
        gameModel.resetChickenPosition();
        assertEquals(chicken.getPosition(), new Position(x/2, y-1));
    }

    @Test
    public void getScoreTest(){
        Chicken chicken = gameModel.getChicken();
        chicken.raiseScore(3);
        assertEquals(3, gameModel.getScore());
    }

    @Test
    public void getLivesTest(){
        Chicken chicken = gameModel.getChicken();
        chicken.removeLife();
        assertEquals(2, gameModel.getLives());
    }

    @Test
    public void isLevelFinishedTest(){
        //TODO acrescentar a condição do vetor as moedas estar vazio
        Chicken chicken = gameModel.getChicken();
        assertFalse(gameModel.isLevelFinished());
        while(chicken.getLives()>0) chicken.removeLife();
        assertTrue(gameModel.isChickenDead());
    }

    @Test
    public void getCurrentLevelIntTest(){
        assertEquals(1, gameModel.getCurrentLevelInt());
        gameModel.increaseLevel();
        assertEquals(2, gameModel.getCurrentLevelInt());
    }

    @Test
    public void isFinalLevelTest(){
        assertFalse(gameModel.isFinalLevel());
        gameModel.increaseLevel();
        assertTrue(gameModel.isFinalLevel());
    }

    @Test
    public void getCurrentLevelTest(){
        assertEquals(level1, gameModel.getCurrentLevel());
    }
}
