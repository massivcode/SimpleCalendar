package com.massivcode.simplecalendar.configs;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 13:17
 */

public class CalendarUiConfig {
    private String yearDelimiter;
    private String monthDelimiter;
    @DrawableRes
    private int previousMonthImageResourceId;
    @DrawableRes
    private int nextMonthImageResourceId;
    @ColorInt
    private int currentYearAndMonthTextColor;

    public CalendarUiConfig(String yearDelimiter, String monthDelimiter, int previousMonthImageResourceId, int nextMonthImageResourceId, int currentYearAndMonthTextColor) {
        this.yearDelimiter = yearDelimiter;
        this.monthDelimiter = monthDelimiter;
        this.previousMonthImageResourceId = previousMonthImageResourceId;
        this.nextMonthImageResourceId = nextMonthImageResourceId;
        this.currentYearAndMonthTextColor = currentYearAndMonthTextColor;
    }

    public String getYearDelimiter() {
        return yearDelimiter;
    }

    public String getMonthDelimiter() {
        return monthDelimiter;
    }

    public int getPreviousMonthImageResourceId() {
        return previousMonthImageResourceId;
    }

    public int getNextMonthImageResourceId() {
        return nextMonthImageResourceId;
    }

    public int getCurrentYearAndMonthTextColor() {
        return currentYearAndMonthTextColor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarUiConfig{");
        sb.append("yearDelimiter='").append(yearDelimiter).append('\'');
        sb.append(", monthDelimiter='").append(monthDelimiter).append('\'');
        sb.append(", previousMonthImageResourceId=").append(previousMonthImageResourceId);
        sb.append(", nextMonthImageResourceId=").append(nextMonthImageResourceId);
        sb.append(", currentYearAndMonthTextColor=").append(currentYearAndMonthTextColor);
        sb.append('}');
        return sb.toString();
    }
}
