package com.massivcode.simplecalendar.models;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 14:53
 */

public class CalendarHeader implements CalendarModel {
    private String title;
    private int color;

    public CalendarHeader(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarHeader{");
        sb.append("title='").append(title).append('\'');
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
