package painting;

import javax.swing.*;

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

        GameMenu demo = new GameMenu();
        f.setJMenuBar(demo.createMenuBar());

        f.add(new GamePanel(9, 9, 15));
        f.setSize(450, 500);
        f.setResizable(false);
        f.setVisible(true);
    }
}

