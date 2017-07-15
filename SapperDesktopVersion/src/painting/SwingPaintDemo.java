package painting;

import game.Cell;

import javax.swing.*;

/**
 * Created by Sergey on 08.07.2017.
 */

public class SwingPaintDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(9, 9, 10));
    }

    private static void createGamePanel(final int row, final int column, final int counterBomb) {
        f.setSize(row * Cell.WIDTH * 2, column * Cell.HEIGHT * 3);
        game = new GamePanel(row, column, counterBomb);
        f.add(game);
    }

    public static void recreateGamePanel(final int row, final int column, final int counterBomb) {
        game.restartGame(row, column, counterBomb);
        f.setSize(row * Cell.WIDTH * 2, column * Cell.HEIGHT * 3);
    }

    private static JFrame f;
    private static GamePanel game;


    private static void createAndShowGUI(final int row, final int column, final int counterBomb) {
        f = new JFrame("Sapper");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMenu menu = new GameMenu();
        f.setJMenuBar(menu.createMenuBar());
        f.validate();

        createGamePanel(row, column, counterBomb);

        Images img = new Images();

        f.setIconImage(img.getBomb());
//        f.setResizable(false);
        f.setVisible(true);
    }
}

