package UI.UIElements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by @author Telnov Sergey on 26.07.2017.
 */
public class GameTimer {
    public GameTimer() {
        startTime = System.currentTimeMillis();
        timerFormat = new SimpleDateFormat("HH:mm:ss");
        timerFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

    }

    public long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }

    public String getCurrentTimeText() {
        long curTime = System.currentTimeMillis() - startTime;
        return timerFormat.format(new Date(curTime));
    }

    public void setNewTimer() {
        startTime = System.currentTimeMillis();
    }

    private long startTime;
    private SimpleDateFormat timerFormat;
}

