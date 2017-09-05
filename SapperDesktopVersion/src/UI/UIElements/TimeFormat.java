package UI.UIElements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by @author Telnov Sergey on 05.09.2017.
 */
public class TimeFormat {

    public static class Format {

        private SimpleDateFormat dateFormat;

        public Format() {
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

    }
    private static Format timeFormat = new Format();

    public static String getTimeText(long time) {
        if (time < 0 || time == Long.MAX_VALUE) {
            return "--:--:--";
        }
        return timeFormat.dateFormat.format(new Date(time));
    }
}
