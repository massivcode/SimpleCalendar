package com.massivcode.simplecalendar.utils;


import com.massivcode.simplecalendar.configs.CalendarDateColorConfig;
import com.massivcode.simplecalendar.models.CalendarColumn;
import com.massivcode.simplecalendar.models.CalendarModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 10:50
 */

public class CalendarGenerator {
    private static CalendarGenerator sInstance = null;
    private Calendar mCalendar;

    private CalendarGenerator() {
        mCalendar = Calendar.getInstance();
    }

    public static CalendarGenerator getInstance() {
        if (sInstance == null) {
            sInstance = new CalendarGenerator();
        }

        return sInstance;
    }

    public List<CalendarModel> generateCalendar(int year, int month, CalendarDateColorConfig calendarConfig) {
        List<CalendarModel> calendarModels = new ArrayList<>();

        // 현재 연도 설정
        mCalendar.set(Calendar.YEAR, year);
        // 현재 월 설정. 월은 0부터 시작
        mCalendar.set(Calendar.MONTH, month - 1);
        // 현재 일 설정. 해당 월의 시작 요일을 알기 위해 1일로 지정
        mCalendar.set(Calendar.DATE, 1);

        // 현재 월의 시작 요일 획득
        // 요일은 일요일 : 1 ~ 토요일 : 7
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);

        // 현재 월의 마지막 일자 획득
        int maximumDate = mCalendar.getActualMaximum(Calendar.DATE);

        // 해당 월의 마지막 요일을 알기 위해 마지막 일자로 현재일 세팅
        mCalendar.set(Calendar.DATE, maximumDate);

        // 현재 월의 마지막 요일 획득
        int maximumDay = mCalendar.get(Calendar.DAY_OF_WEEK);

        // 리얼 데이터 시작 인덱스. 이 앞은 전부 더미 데이터
        int dataStartIndex = dayOfWeek - 1;

        // 리얼 데이터 종료 인덱스
        int dataEndIndex = maximumDate - 1 + dataStartIndex;

        // 캘린더 종료 인덱스.
        int calendarEndIndex = maximumDay == 7 ? maximumDate + dataStartIndex - 1 : maximumDate - 1 + (7 - maximumDay);

        for (int i = 0; i <= calendarEndIndex; i++) {
            if (i < dataStartIndex || i > dataEndIndex) {
                calendarModels.add(new CalendarColumn(true));
            } else {
                int date = i - dataStartIndex + 1;
                int day = (i % 7) + 1;
                int color = calendarConfig.getWeekDayColor();

                switch (day) {
                    case 1:
                        color = calendarConfig.getSundayColor();
                        break;
                    case 7:
                        color = calendarConfig.getSaturdayColor();
                        break;
                    default:
                        color = calendarConfig.getWeekDayColor();
                        break;
                }

                calendarModels.add(new CalendarColumn(date, day, color, month, year));
            }
        }

        return calendarModels;
    }

    public int getCurrentYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public int getCurrentMonth() {
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    public int getCurrentDate() {
        return mCalendar.get(Calendar.DATE);
    }
}
