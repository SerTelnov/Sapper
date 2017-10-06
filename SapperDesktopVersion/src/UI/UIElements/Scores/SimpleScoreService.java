package UI.UIElements.Scores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class SimpleScoreService implements ScoreService {
    protected Map<Difficulty, TreeSet<Integer>> results;
    private int maxScores;

    public SimpleScoreService() {
        this(20);
    }

    public SimpleScoreService(int maxScores) {
        results = new HashMap<>();
        for (Difficulty difficulty : Difficulty.values()) {
            results.put(difficulty, new TreeSet<>());
        }

        this.maxScores = maxScores;
    }

    @Override
    public void addScore(int score, Difficulty difficulty) {
        TreeSet<Integer> a = results.get(difficulty);
        a.add(score);
        if (a.size() > maxScores) {
            Iterator<Integer> it = a.descendingIterator();
            it.next();
            it.remove();
        }
    }

    @Override
    public TreeSet<Integer> getScore(Difficulty difficulty) {
        return results.get(difficulty);
    }

    @Override
    public int getBestScore(Difficulty difficulty) {
        return results.get(difficulty).descendingIterator().next();
    }

    protected void print(Difficulty difficulty) {
        for (Integer i : results.get(difficulty)) {
            System.out.println(i + " ");
        }
    }

    public static void main(String[] args) {
        SimpleScoreService a = new SimpleScoreService(3);
        a.addScore(1, Difficulty.EASY);
        a.addScore(2, Difficulty.EASY);
        a.addScore(3, Difficulty.EASY);
        a.addScore(4, Difficulty.EASY);
        a.addScore(-1, Difficulty.EASY);
        a.print(Difficulty.EASY);
    }
}
