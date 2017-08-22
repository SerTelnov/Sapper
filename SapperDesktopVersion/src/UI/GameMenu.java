package UI;

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

        startNewGame = menuItemFactory("new game");
        gameSetting.add(startNewGame);

        difficultyLevel = new JMenu("level difficulty");

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
    private JMenu gameSetting;
    private JMenuItem difficultyLevel, startNewGame,
            easy, normal, hard, intense, beginner;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(easy)) {
            SwingPaint.recreatePanels(9, 9, 10);
        } else if (source.equals(normal)) {
            SwingPaint.recreatePanels(16, 16, 40);
        } else if (source.equals(hard)) {
            SwingPaint.recreatePanels(30, 16, 99);
        } else if (source.equals(intense)) {
            SwingPaint.recreatePanels(40, 17, 200);
        } else if (source.equals(beginner)) {
            SwingPaint.recreatePanels(5, 5, 5);
        } else if (source.equals(startNewGame)) {
            SwingPaint.recreatePanels(9, 9, 10);
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
