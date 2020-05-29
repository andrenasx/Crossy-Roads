package crossyroads.model;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        if((a.getLevel() - b.getLevel())!=0) return b.getLevel() - a.getLevel();
        if((a.getCoins() - b.getCoins())!=0) return b.getCoins() - a.getCoins();
        if((a.getSteps() - b.getSteps())!=0) return a.getSteps() - b.getSteps();
        return 1;
    }
}
