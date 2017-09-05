package UI.UIElements;

import UI.panels.IScorePanelListener;
import UI.panels.ScorePanel;

import java.util.ArrayList;

/**
 * Created by @author Telnov Sergey on 26.07.2017.
 */

///// coming soon

public class LeaderBoard implements IScorePanelListener {
    private ArrayList<ILeaderBoardListener> listeners = new ArrayList<>();
    private long beginnerLevelScore = Long.MAX_VALUE;
    private long easyLevelScore = Long.MAX_VALUE;
    private long normalLevelScore = Long.MAX_VALUE;
    private long hardLevelScore = Long.MAX_VALUE;
    private long intenseLevelScore = Long.MAX_VALUE;

    public void addListener(ILeaderBoardListener listener) {
        listeners.add(listener);
    }

    @Override
    public void newScore(long score, LevelDifficulty levelDifficulty) {
        boolean scoreChanged = false;
        switch (levelDifficulty) {
            case BEGINNER:
                if (beginnerLevelScore > score) {
                    beginnerLevelScore = score;
                    scoreChanged = true;
                }
                break;
            case EASY:
                if (easyLevelScore > score) {
                    easyLevelScore = score;
                    scoreChanged = true;
                }
                break;
            case NORMAL:
                if (normalLevelScore > score) {
                    normalLevelScore = score;
                    scoreChanged = true;
                }
                break;
            case HARD:
                if (hardLevelScore > score) {
                    hardLevelScore = score;
                    scoreChanged = true;
                }
                break;
            case INTENSE:
                if (intenseLevelScore > score) {
                    intenseLevelScore = score;
                    scoreChanged = true;
                }
                break;
        }
        if (scoreChanged) {
            sayScoreChanged(score, levelDifficulty);
        }
    }

    public String getBeginnerLevelResult() {
        return TimeFormat.getTimeText(beginnerLevelScore);
    }

    public String getEasyLevelResult() {
        return TimeFormat.getTimeText(easyLevelScore);
    }

    public String getNormalLevelResult() {
        return TimeFormat.getTimeText(normalLevelScore);
    }

    public String getHardLevelResult() {
        return TimeFormat.getTimeText(hardLevelScore);
    }
    public String getIntenseLevelResult() {
        return TimeFormat.getTimeText(intenseLevelScore);
    }

    private void sayScoreChanged(long score, LevelDifficulty levelDifficulty) {
        for (ILeaderBoardListener listener: listeners) {
            listener.scoreChanged(TimeFormat.getTimeText(score), levelDifficulty);
        }
    }
}
