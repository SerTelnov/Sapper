package painting;

import java.awt.*;

/**
 * Created by Sergey on 10.07.2017.
 */

public class ScorePainter {
    public ScorePainter(final int bombsCounter) {
        this.score = 0;
        this.bombsCounter = bombsCounter;
    }
    public void changeScore(final int newScore) { score = newScore; }
    public void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Flags: " + score + "/" + bombsCounter, 10, 30);
    }
    private int score;
    private int bombsCounter;
}
