package painting;

import java.awt.*;

/**
 * Created by Sergey on 10.07.2017.
 */

public class ScorePainter {
    public ScorePainter(final int score) {
        this.score = score;
    }
    public void changeScore(final int newScore) { score = newScore; }
    public void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Number of defused bombs: '" + score + "'", 10, 40);
    }
    private int score;
}
