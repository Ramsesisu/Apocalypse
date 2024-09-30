package org.apocalypse.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {

    private static final SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

    public static long getCurrent() {
        return System.currentTimeMillis();
    }

    public static String getFormattedCurrentTime() {
        return formatTime.format(getCurrent());
    }

    public static String getFormattedCurrentDate() {
        return formatDate.format(getCurrent());
    }

    public static String getFormattedCurrentDateTime() {
        return getFormattedCurrentDate() + " " + getFormattedCurrentTime();
    }

    public static String getFormattedTime(long time) {
        return formatTime.format(time);
    }

    public static String getFormattedDate(long time) {
        return formatDate.format(time);
    }

    public static String getFormattedDateTime(long time) {
        return getFormattedDate(time) + " " + getFormattedTime(time);
    }

    public static long getTimeFromFormatted(String time) {
        try {
            return formatTime.parse(time).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public static long getDateFromFormatted(String time) {
        try {
            return formatDate.parse(time).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public static int getSecondsSince(long time) {
        return (int) ((getCurrent() - time) / 1000);
    }

    public static int getMinutesSince(long time) {
        return getSecondsSince(time) / 60;
    }

    public static int getHoursSince(long time) {
        return getMinutesSince(time) / 60;
    }

    public static int getDaysSince(long time) {
        return getHoursSince(time) / 24;
    }

    public static int getWeeksSince(long time) {
        return getDaysSince(time) / 7;
    }

    public static int getMonthsSince(long time) {
        return getDaysSince(time) / 30;
    }

    public static int getYearsSince(long time) {
        return getDaysSince(time) / 365;
    }

    public static boolean isInRange(long date) {
        return isInRange(date, 0, getCurrent());
    }

    public static boolean isInRange(long date, long start, long end) {
        return date >= start && date <= end;
    }

    public static long getRanged(long date) {
        return getRanged(date, 0, getCurrent());
    }

    public static long getRanged(long date, long start, long end) {
        return Math.max(start, Math.min(date, end));
    }
}
