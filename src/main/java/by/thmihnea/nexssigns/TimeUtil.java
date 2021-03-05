package by.thmihnea.nexssigns;

public class TimeUtil {

    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;

    public static String getTimeLeft(long timeLeft) {
        int weeks = (int) (timeLeft / WEEK);
        int days = (int) ((timeLeft % WEEK) / DAY);
        int hours = (int) ((timeLeft % DAY) / HOUR);
        int minutes = (int) ((timeLeft % HOUR) / MINUTE);
        int seconds = (int) ((timeLeft % MINUTE) / SECOND);

        StringBuilder stringBuilder = new StringBuilder();
        if (weeks != 0) stringBuilder.append(weeks).append("w ");
        if (days != 0) stringBuilder.append(days).append("d ");
        if (hours != 0) stringBuilder.append(hours).append("h ");
        if (minutes != 0) stringBuilder.append(minutes).append("m ");
        if (seconds != 0) stringBuilder.append(seconds).append("s");
        return stringBuilder.toString();
    }
}
