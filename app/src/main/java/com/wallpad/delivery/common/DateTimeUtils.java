package com.wallpad.delivery.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    private final static String pattern_year = "yyyy";
    private final static String pattern_month = "MM";
    private final static String pattern_day = "dd";
    private final static String pattern_DateOfWeek = "EEEE";
    private final static String pattern_AMorPM = "a";
    private final static String pattern_hour_12 = "hh:mm";

    public String getYearCurrent() {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_year, Locale.getDefault());
        return sdf.format(new Date());
    }

    public String getMonthCurrent() {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_month, Locale.getDefault());
        return sdf.format(new Date());
    }

    public String getDayCurrent() {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_day, Locale.getDefault());
        return sdf.format(new Date());
    }

    public String getDateOfWeekCurrent() { // monday, tuesday,...
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_DateOfWeek, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * Get current time info
     *
     * @return AM or PM
     */
    public String getAMorPMTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_AMorPM, Locale.getDefault());
        return sdf.format(date);
    }

    public String get12hourTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_hour_12, Locale.getDefault());
        return sdf.format(date);
    }

}
