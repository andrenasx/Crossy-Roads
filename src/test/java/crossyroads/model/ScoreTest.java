package crossyroads.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
    @Test
    public void getScoreTest(){
        Random rand = new Random();
        int level = rand.nextInt(5);
        int coins = rand.nextInt(40);
        int steps = rand.nextInt(50);
        Score score = new Score(level, coins, steps);
        assertEquals(level, score.getLevel());
        assertEquals(coins, score.getCoins());
        assertEquals(steps, score.getSteps());
    }

    @Test
    public void setScoreTest(){
        Score score = new Score(3, 23, 34);
        Random rand = new Random();
        int level = rand.nextInt(5);
        int coins = rand.nextInt(40);
        int steps = rand.nextInt(50);
        score.setCoins(coins);
        assertEquals(coins, score.getCoins());
        score.setLevel(level);
        assertEquals(level, score.getLevel());
        score.setSteps(steps);
        assertEquals(steps, score.getSteps());
    }
}
