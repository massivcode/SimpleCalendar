package com.massivcode.simplecalendar.configs;

import android.support.annotation.ColorInt;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 12:32
 */

public class CalendarDateColorConfig {
    @ColorInt
    private int sundayColor;
    @ColorInt
    private int saturdayColor;
    @ColorInt
    private int weekDayColor;

    public CalendarDateColorConfig(@ColorInt int sundayColor, @ColorInt int saturdayColor, @ColorInt int weekDayColor) {
        this.sundayColor = sundayColor;
        this.saturdayColor = saturdayColor;
        this.weekDayColor = weekDayColor;
    }

    @ColorInt
    public int getSundayColor() {
        return sundayColor;
    }

    @ColorInt
    public int getSaturdayColor() {
        return saturdayColor;
    }

    @ColorInt
    public int getWeekDayColor() {
        return weekDayColor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarConfig{");
        sb.append("sundayColor=").append(sundayColor);
        sb.append(", saturdayColor=").append(saturdayColor);
        sb.append(", weekDayColor=").append(weekDayColor);
        sb.append('}');
        return sb.toString();
    }
}
