package UI.menu;

import UI.UIElements.ILeaderBoardListener;
import UI.UIElements.LeaderBoard;
import UI.UIElements.LevelDifficulty;

import javax.swing.*;

/**
 * Created by @author Telnov Sergey on 05.09.2017.
 */

public class ScoresMenu extends JMenu implements ILeaderBoardListener {

    private JMenuItem beginner, easy, normal, hard, intense;
    private LeaderBoard leaderBoard;

    public ScoresMenu(LeaderBoard currLeaderBoard) {
        super("Scores");

        leaderBoard = currLeaderBoard;
        leaderBoard.addListener(this);

        beginner = menuItemFactory("Beginner", leaderBoard.getBeginnerLevelResult());
        this.add(beginner);
        easy = menuItemFactory("Easy", leaderBoard.getEasyLevelResult());
        this.add(easy);
        normal = menuItemFactory("Normal", leaderBoard.getNormalLevelResult());
        this.add(normal);
        hard = menuItemFactory("Hard", leaderBoard.getHardLevelResult());
        this.add(hard);
        intense = menuItemFactory("Intense", leaderBoard.getIntenseLevelResult());
        this.add(intense);
    }

    private String getMenuItemName(String name, String score) {
        return name + ": " + score;
    }

    private JMenuItem menuItemFactory(String level, String score) {
        String name = getMenuItemName(level, score);
        JMenuItem mit = new JMenuItem(name);
        mit.setSelected(true);
        return mit;
    }

    @Override
    public void scoreChanged(String newScore, LevelDifficulty levelDifficulty) {
        switch (levelDifficulty) {
            case BEGINNER:
                beginner.setText(getMenuItemName("Beginner", newScore));
                break;
            case EASY:
                easy.setText(getMenuItemName("Easy", newScore));
                break;
            case NORMAL:
                normal.setText(getMenuItemName("Normal", newScore));
                break;
            case HARD:
                hard.setText(getMenuItemName("Hard", newScore));
                break;
            case INTENSE:
                intense.setText(getMenuItemName("Intense", newScore));
                break;
        }
    }
}
