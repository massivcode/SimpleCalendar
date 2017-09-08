package com.massivcode.simplecalendar.configs;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 13:34
 */

public class CalendarStartDateConfig {
    private int year;
    private int month;

    public CalendarStartDateConfig(int year, int month) {
        this.year = year;

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month");
        }

        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarStartDateConfig{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append('}');
        return sb.toString();
    }
}
