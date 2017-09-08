package com.massivcode.simplecalendar.models;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 10:49
 */

public class Event {
    private int id;
    private String dayId;
    private String data;

    public int getId() {
        return id;
    }

    public String getDayId() {
        return dayId;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("id=").append(id);
        sb.append(", dayId='").append(dayId).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
