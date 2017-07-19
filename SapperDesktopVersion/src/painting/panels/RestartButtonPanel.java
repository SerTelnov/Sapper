package painting.panels;

import painting.ImagesGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 19.07.2017.
 */

public class RestartButtonPanel extends JPanel {
    private JButton restartButton;
    private PanelTop panelTop;
    private boolean isSad = false;

    public RestartButtonPanel(PanelTop panelTop) {
        this.panelTop = panelTop;
        this.setBackground(Color.GRAY);
        this.setFont( GamePanel.font );
        setLayout( new BorderLayout() );

        restartButton = PanelTop.buttonFactory(ImagesGetter.SMILE_ICON);
        add(restartButton, BorderLayout.CENTER);
        JLabel emptyLabel = new JLabel(ImagesGetter.SMILE_ICON);
        emptyLabel.setVisible( false );
        add(emptyLabel, BorderLayout.LINE_START);
        add(emptyLabel, BorderLayout.LINE_END);
        ActionListener restartButtonListener = e -> restartGame();
        restartButton.addActionListener(restartButtonListener);
    }

    public void setSmileIcon() {
        if (isSad) {
            restartButton.setIcon(ImagesGetter.SMILE_ICON);
            isSad = false;
        }
    }

    public void setSadIcon() {
        if (!isSad) {
            restartButton.setIcon(ImagesGetter.SAD_ICON);
            isSad = true;
        }
    }

    private void restartGame() {
        panelTop.restartGame();
    }
}
