package painting;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 08.07.2017.
 */

public class SwingPaintDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Sapper");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMenu menu = new GameMenu();
        f.setJMenuBar(menu.createMenuBar());
        f.setContentPane(menu.createContentPane());

        int row = 5;
        int column = 5;
        int counterBomb = 5;

        f.setSize(row * 70, column * 90);
        f.add(new GamePanel(row, column, counterBomb));
        f.setResizable(false);
        f.setVisible(true);
    }
}

