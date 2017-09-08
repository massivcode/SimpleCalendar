package com.massivcode.simplecalendar.listeners;


import com.massivcode.simplecalendar.models.CalendarColumn;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 12:41
 */

public interface CalendarCallback {
    void onDateClick(CalendarColumn calendarModel);
}
