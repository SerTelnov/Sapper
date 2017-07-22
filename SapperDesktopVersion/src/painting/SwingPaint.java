package painting;

import game.Cell;
import game.Game;
import painting.panels.GamePanel;
import painting.panels.GamePanelListener;
import painting.panels.PanelTop;
import painting.panels.PanelTopListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 08.07.2017.
 */

public class SwingPaint {

    public static final int RIGHT_LEFT_PADDING = 68 * 2;
    public static final int TOP_PADDING = 160;
    public static final int BOTTOM_PADDING = 20;
    private static PanelTop panelTop;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingPaint::createAndShowGUI);
    }

    private static void setFrameSize(final int row, final int column) {
        f.setSize(row * Cell.WIDTH + RIGHT_LEFT_PADDING,
                column * Cell.HEIGHT + TOP_PADDING + BOTTOM_PADDING);
    }

    private static void createPanels(final int row, final int column, final int counterBomb) {
        setFrameSize(row, column);
        Game game = new Game(row, column, counterBomb, false);
        PanelTopListener topListener = new PanelTopListener();
        GamePanelListener gpl = new GamePanelListener();
        panelTop = new PanelTop(game, topListener, gpl);
        GamePanel gamePanel = new GamePanel(game, topListener, gpl);
        f.add(panelTop, BorderLayout.PAGE_START);
        f.add(gamePanel, BorderLayout.CENTER);
    }

    public static void recreatePanels(final int row, final int column, final int counterBomb) {
        Game game = new Game(row, column, counterBomb, false);
        panelTop.recreatePanel(game);
        setFrameSize(row, column);
    }

    private static JFrame f;

    private static void createAndShowGUI() {
        f = new JFrame("Sapper");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMenu menu = new GameMenu();
        f.setJMenuBar(menu.createMenuBar());
        f.validate();
        f.setLayout(new BorderLayout());
        f.setResizable( false );
        createPanels(9, 9, 15);

        f.setIconImage(ImagesGetter.GAME_ICON);
        f.setVisible(true);
    }
}

