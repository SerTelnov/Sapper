package UI.menu;

import UI.SwingPaint;
import UI.UIElements.LeaderBoard;
import UI.UIElements.LevelDifficulty;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sergey on 11.07.2017.
 */

public class GameMenu implements MenuListener, ActionListener, KeyListener {
    public GameMenu(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public JMenuBar createMenuBar() {
        Font font = new Font("sans-serif", Font.PLAIN, 20);
        UIManager.put("MenuBar.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        menuBar = new JMenuBar();
        menuBar.setFont(font);

        gameSetting = new JMenu("Game");
        gameSetting.setMnemonic(KeyEvent.VK_A);
        menuBar.add(gameSetting);

        startNewGame = menuItemFactory("New game");
        gameSetting.add(startNewGame);

        difficultyLevel = new JMenu("Level difficulty");
        gameSetting.addSeparator();

        beginner = menuItemFactory("Beginner");
        difficultyLevel.add(beginner);
        easy = menuItemFactory("Easy");
        difficultyLevel.add(easy);
        normal = menuItemFactory("Normal");
        difficultyLevel.add(normal);
        hard = menuItemFactory("Hard");
        difficultyLevel.add(hard);
        intense = menuItemFactory("Intense");
        difficultyLevel.add(intense);
        gameSetting.add(difficultyLevel);
        gameSetting.add(new ScoresMenu(this.leaderBoard));
        gameSetting.addSeparator();

        exitButton = menuItemFactory("Exit");
//        exitButton = new JMenuItem("Exit");
        gameSetting.add(exitButton);
        return menuBar;
    }

    private JMenuItem menuItemFactory(String name) {
        JMenuItem mit = new JMenuItem(name);
        mit.setSelected(true);
        mit.setMnemonic(KeyEvent.VK_R);
        mit.addActionListener(this);
        return mit;
    }

    private JMenuBar menuBar;
    private JMenu gameSetting, difficultyLevel;
    private JMenuItem startNewGame, exitButton,
        beginner, easy, normal, hard, intense;
    private LeaderBoard leaderBoard;


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(beginner)) {
            SwingPaint.recreatePanels(LevelDifficulty.BEGINNER);
        } else if (source.equals(easy)) {
            SwingPaint.recreatePanels(LevelDifficulty.EASY);
        } else if (source.equals(normal)) {
            SwingPaint.recreatePanels(LevelDifficulty.NORMAL);
        } else if (source.equals(hard)) {
            SwingPaint.recreatePanels(LevelDifficulty.HARD);
        } else if (source.equals(intense)) {
            SwingPaint.recreatePanels(LevelDifficulty.INTENSE);
        } else if (source.equals(startNewGame)) {
            SwingPaint.recreatePanels(LevelDifficulty.NO_LEVEL);
        } else if (source.equals(exitButton)) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void menuSelected(MenuEvent e) {
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
