package painting;

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

//    public GameMenu() {
////        menuBar = new JMenuBar();
////
////        gameSetting = new JMenu("Game");
////        gameSetting.setMnemonic(KeyEvent.VK_S);
////        gameSetting.addMenuListener(this);
////        menuBar.add(gameSetting);
////
////        difficultyLevel = new JMenuItem("Difficulty level");
////        difficultyLevel.setMnemonic(KeyEvent.VK_D);
////        gameSetting.add(difficultyLevel);
////
////        easy = new JMenuItem("Easy");
////        easy.addActionListener(this);
////        difficultyLevel.add(easy);
////
////        normal = new JMenuItem("Normal");
////        normal.addActionListener(this);
////        difficultyLevel.add(normal);
////
////        hard = new JMenuItem("Hard");
////        hard.addActionListener(this);
////        difficultyLevel.add(hard);
////
////        intense = new JMenuItem("Intense");
////        intense.addActionListener(this);
////        difficultyLevel.add(intense);
////
//////        coming later
//////        JMenuItem yourGame = new JMenuItem("your game");
////////        add listener
//////        difficultyLevel.add(yourGame);
////
////        menuBar.setVisible(true);
//
//    }

//    JTextArea output;
//    JScrollPane scrollPane;

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();

        gameSetting = new JMenu("Setting");
        gameSetting.setMnemonic(KeyEvent.VK_A);
        menuBar.add(gameSetting);

        difficultyLevel = new JMenu("level difficulty");

        gameSetting.addSeparator();
//        ButtonGroup group = new ButtonGroup();

        easy = createMenuItem("Easy");
        difficultyLevel.add(easy);
//        group.add(easy);
        normal = createMenuItem("Normal");
        difficultyLevel.add(normal);
        hard = createMenuItem("Hard");
        difficultyLevel.add(hard);
        intense = createMenuItem("Intense");
        difficultyLevel.add(intense);
        gameSetting.add(difficultyLevel);

        return menuBar;
    }

    private JMenuItem createMenuItem(String name) {
        JMenuItem mit = new JMenuItem(name);
        mit.setSelected(true);
        mit.setMnemonic(KeyEvent.VK_R);
        mit.addActionListener(this);
        return mit;
    }

    private JMenuBar menuBar;
    private JMenu gameSetting;
    private JMenuItem difficultyLevel,
            easy, normal, hard, intense;


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(easy)) {
            SwingPaintDemo.recreateGamePanel(5, 5, 5);
        } else if (source.equals(normal)) {
            SwingPaintDemo.recreateGamePanel(9, 9, 15);
        } else if (source.equals(hard)) {
            SwingPaintDemo.recreateGamePanel(30, 15, 99);
        } else if (source.equals(intense)) {
            SwingPaintDemo.recreateGamePanel(40, 20, 200);
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
