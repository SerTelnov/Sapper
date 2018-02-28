package UI.panels;

import UI.UIElements.ImagesGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 19.07.2017.
 */

public class RestartButtonPanel extends JPanel {
    private JButton restartButton;
    private PanelTop panelTop;

    private Timer boredModeTimer;
    private final int BORED_MODE_TIMER = 150;

    public RestartButtonPanel(PanelTop panelTop) {
        this.panelTop = panelTop;
        this.setBackground( Color.GRAY );
        this.setFont( GamePanel.font );
        setLayout( new BorderLayout() );

        ActionListener boredModeTimerListener = event -> {
            restartButton.setIcon(ImagesGetter.SMILE_ICON);
            boredModeTimer.stop();
        };

        boredModeTimer = new Timer(BORED_MODE_TIMER, boredModeTimerListener);

        restartButton = PanelTop.buttonFactory(ImagesGetter.SMILE_ICON);
        add(restartButton, BorderLayout.CENTER);

        JLabel emptyLabel = new JLabel(ImagesGetter.SMILE_ICON);
        emptyLabel.setVisible( false );
        add(emptyLabel, BorderLayout.LINE_START);
        add(emptyLabel, BorderLayout.LINE_END);
        ActionListener restartButtonListener = e -> restartGame();
        restartButton.addActionListener(restartButtonListener);
    }

    public void setFinalIcon(boolean isWin) {
        if (isWin) {
            restartButton.setIcon(ImagesGetter.HAPPY_ICON);
        } else {
            restartButton.setIcon(ImagesGetter.SAD_ICON);
        }
    }

    public void restart() {
        restartButton.setIcon(ImagesGetter.SMILE_ICON);
    }

    private void restartGame() {
        panelTop.restartGame();
        restart();
    }

    public void setBoredIcon() {
        restartButton.setIcon(ImagesGetter.BORED_ICON);

        boredModeTimer.setInitialDelay(BORED_MODE_TIMER);
        boredModeTimer.start();
    }
}