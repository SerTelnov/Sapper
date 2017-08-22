package UI;

import UI.UIElements.GameTimer;
import UI.UIElements.ImagesGetter;
import UI.panels.*;
import game.ActionField;

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
        f.setSize(row * FieldPainter.CELL_WIDTH + RIGHT_LEFT_PADDING,
                column * FieldPainter.CELL_HEIGHT + TOP_PADDING + BOTTOM_PADDING);
    }

    private static void createPanels(final int row, final int column, final int counterBomb) {
        setFrameSize(row, column);
        ActionField actionField = new game.ActionField(row, column, counterBomb);
        GameTimer gameTimer = new GameTimer();
        PanelTopListener topListener = new PanelTopListener();
        GamePanelListener gpl = new GamePanelListener();
        panelTop = new PanelTop(actionField, topListener, gpl, gameTimer);
        GamePanel gamePanel = new GamePanel(actionField, topListener, gpl);
        f.add(panelTop, BorderLayout.PAGE_START);
        f.add(gamePanel, BorderLayout.CENTER);
    }

    public static void recreatePanels(final int row, final int column, final int counterBomb) {
        ActionField actionField = new ActionField(row, column, counterBomb);
        panelTop.recreatePanel(actionField);
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
        createPanels(9, 9, 10);

        f.setIconImage(ImagesGetter.GAME_ICON);
        f.setVisible(true);
    }
}

