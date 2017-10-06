package score;

import java.util.TreeSet;

public interface ScoreService {

    void addScore(int score, Difficulty difficulty);

    TreeSet<Integer> getScore(Difficulty difficulty);

    int getBestScore(Difficulty difficulty);
}
