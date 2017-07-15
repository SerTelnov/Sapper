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

    public JMenuBar createMenuBar() {

        Font f = new Font("sans-serif", Font.PLAIN, 20);
        UIManager.put("MenuBar.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        menuBar = new JMenuBar();
        menuBar.setFont(f);

        gameSetting = new JMenu("Setting");
        gameSetting.setMnemonic(KeyEvent.VK_A);
        menuBar.add(gameSetting);

        difficultyLevel = new JMenu("level difficulty");

        gameSetting.addSeparator();

        easy = createMenuItem("Easy");
        difficultyLevel.add(easy);
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
