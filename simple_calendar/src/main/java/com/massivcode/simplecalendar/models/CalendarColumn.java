package com.massivcode.simplecalendar.models;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 10:49
 */

public class CalendarColumn implements CalendarModel {
    private String id;
    private int date;
    private int dayOfWeek;
    private int month;
    private int year;
    private int color;
    private List<Event> events;
    private boolean isDummy;

    public CalendarColumn(int date, int dayOfWeek, int color, int month, int year) {
        this.id = year + "-" + month + "-" + date;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.color = color;
        this.month = month;
        this.year = year;
    }

    public CalendarColumn(boolean isDummy) {
        this.isDummy = isDummy;
    }

    public String getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getColor() {
        return color;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarModel{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", dayOfWeek=").append(dayOfWeek);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append(", color=").append(color);
        sb.append(", events=").append(events);
        sb.append(", isDummy=").append(isDummy);
        sb.append('}');
        return sb.toString();
    }
}
