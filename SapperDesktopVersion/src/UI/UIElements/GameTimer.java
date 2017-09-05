package UI.UIElements;

/**
 * Created by @author Telnov Sergey on 26.07.2017.
 */

public class GameTimer {
    public GameTimer() {
        startTime = System.currentTimeMillis();
    }

    public long getCurrentTime() {
        if (startTime < 0) {
            return 0;
        } else if (endTime >= 0) {
            return endTime - startTime;
        }
        return System.currentTimeMillis() - startTime;
    }

    public void setZeroTime() {
        this.startTime = -1;
        this.endTime = -1;
    }

    public String getCurrentTimeText() {
        long result = getCurrentTime();
        if (result == 0) {
            return ZERO_TIME;
        }
        return TimeFormat.getTimeText(result);
    }

    public void setNewTimer() {
        startTime = System.currentTimeMillis();
    }

    public void stopTime() {
        endTime = System.currentTimeMillis();
    }

    private long startTime;
    private long endTime = -1;
    private final String ZERO_TIME = "00:00:00";
}

