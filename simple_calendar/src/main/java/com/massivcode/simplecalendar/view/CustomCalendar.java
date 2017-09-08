package com.massivcode.simplecalendar.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.massivcode.simplecalendar.R;
import com.massivcode.simplecalendar.adapter.CustomCalendarAdapter;
import com.massivcode.simplecalendar.configs.CalendarDateColorConfig;
import com.massivcode.simplecalendar.configs.CalendarHeaderConfig;
import com.massivcode.simplecalendar.configs.CalendarStartDateConfig;
import com.massivcode.simplecalendar.configs.CalendarUiConfig;
import com.massivcode.simplecalendar.listeners.CalendarCallback;
import com.massivcode.simplecalendar.utils.CalendarGenerator;

import java.util.Arrays;


/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 12:39
 */

public class CustomCalendar extends LinearLayout {
    private static final String YEAR_MONTH_FORMAT = "{Y}{YD} {M}{MD}";
    private static final String DEFAULT_YEAR_DELIMITER = "년";
    private static final String DEFAULT_MONTH_DELIMITER = "월";
    private static final int mDefaultSundayColor = Color.parseColor("#F44336");
    private static final int mDefaultSaturdayColor = Color.parseColor("#2196f3");
    private static final int mDefaultWeekdayColor = Color.parseColor("#000000");
    private static final int mDefaultCurrentYearAndMonthTextColor = Color.parseColor("#000000");
    private static final int mDefaultMovePreviousIcon = R.drawable.default_arrow_left;
    private static final int mDefaultMoveNextIcon = R.drawable.default_arrow_right;

    private LinearLayout mRootContainer;

    private LinearLayout mIndicatorContainer;

    private ImageView mMovePreviousMonthImageView;

    private TextView mCurrentYearAndMonthTextView;

    private ImageView mMoveNextMonthImageView;

    private RelativeLayout mRecyclerViewContainer;
    private RecyclerView mRecyclerView;

    private String mYearDelimiter = DEFAULT_YEAR_DELIMITER;
    private String mMonthDelimiter = DEFAULT_MONTH_DELIMITER;
    private int mSundayColor = mDefaultSundayColor;
    private int mSaturdayColor = mDefaultSaturdayColor;
    private int mWeekdayColor = mDefaultWeekdayColor;
    private int mCurrentYearAndMonthTextColor = mDefaultCurrentYearAndMonthTextColor;
    private int mMovePreviousIcon = mDefaultMovePreviousIcon;
    private int mMoveNextIcon = mDefaultMoveNextIcon;

    private CalendarGenerator mCalendarGenerator = CalendarGenerator.getInstance();
    private int mCurrentYear = mCalendarGenerator.getCurrentYear(),
            mCurrentMonth = mCalendarGenerator.getCurrentMonth();

    private CalendarCallback mCalendarCallback;
    private CustomCalendarAdapter mCustomCalendarAdapter;

    public CustomCalendar(Context context) {
        this(context, null);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    private void initialize() {
        Context context = getContext();

        inflate(context, R.layout.layout_custom_calendar, this);

        mRootContainer = (LinearLayout) findViewById(R.id.custom_calendar_root_container);
        mIndicatorContainer = (LinearLayout) findViewById(R.id.custom_calendar_indicator_container);
        mMovePreviousMonthImageView = (ImageView) findViewById(R.id.custom_calendar_move_previous_iv);
        mCurrentYearAndMonthTextView = (TextView) findViewById(R.id.custom_calendar_current_year_month_tv);
        mMoveNextMonthImageView = (ImageView) findViewById(R.id.custom_calendar_move_next_iv);
        mRecyclerViewContainer = (RelativeLayout) findViewById(R.id.custom_calendar_rv_container);
        mRecyclerView = (RecyclerView) findViewById(R.id.custom_calendar_rv);

        mCustomCalendarAdapter = new CustomCalendarAdapter(
                new CalendarHeaderConfig(Arrays.asList("일", "월", "화", "수", "목", "금", "토"), Arrays.asList(mSundayColor, mWeekdayColor, mWeekdayColor, mWeekdayColor, mWeekdayColor, mWeekdayColor, mSaturdayColor)),
                mCalendarGenerator.generateCalendar(mCurrentYear, mCurrentMonth,
                        new CalendarDateColorConfig(mSundayColor, mSaturdayColor, mWeekdayColor)),
                mCalendarCallback);

        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.default_divider);
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(divider, divider, 7));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRecyclerView.setAdapter(mCustomCalendarAdapter);

        mMovePreviousMonthImageView.setImageResource(mMovePreviousIcon);
        mMoveNextMonthImageView.setImageResource(mMoveNextIcon);
        mCurrentYearAndMonthTextView.setTextColor(mCurrentYearAndMonthTextColor);

        setCurrentYearAndMonth(mCurrentYear, mCurrentMonth);
    }

    public void setup(CalendarStartDateConfig calendarStartDateConfig) {
        setup(null, null, calendarStartDateConfig);
    }

    public void setup(CalendarDateColorConfig dateColorConfig) {
        setup(dateColorConfig, null, null);
    }

    public void setup(CalendarUiConfig calendarUiConfig) {
        setup(null, calendarUiConfig, null);
    }

    public void setup(CalendarDateColorConfig dateColorConfig, CalendarUiConfig calendarUiConfig, CalendarStartDateConfig startDateConfig) {
        if (dateColorConfig != null) {
            mSundayColor = dateColorConfig.getSundayColor();
            mSaturdayColor = dateColorConfig.getSaturdayColor();
            mWeekdayColor = dateColorConfig.getWeekDayColor();
        }

        if (calendarUiConfig != null) {
            mYearDelimiter = calendarUiConfig.getYearDelimiter();
            mMonthDelimiter = calendarUiConfig.getMonthDelimiter();
            mCurrentYearAndMonthTextColor = calendarUiConfig.getCurrentYearAndMonthTextColor();
            mMovePreviousIcon = calendarUiConfig.getPreviousMonthImageResourceId();
            mMoveNextIcon = calendarUiConfig.getNextMonthImageResourceId();

            mMovePreviousMonthImageView.setImageResource(mMovePreviousIcon);
            mMoveNextMonthImageView.setImageResource(mMoveNextIcon);
            mCurrentYearAndMonthTextView.setTextColor(mCurrentYearAndMonthTextColor);
        }

        if (startDateConfig != null) {
            mCurrentYear = startDateConfig.getYear();
            mCurrentMonth = startDateConfig.getMonth();
        }

        invalidate();
    }

    public void setCalendarCallback(CalendarCallback callback) {
        mCalendarCallback = callback;
    }

    protected void onPreviousMonthClick(View view) {
        int tempMonth = mCurrentMonth - 1;
        int tempYear = mCurrentYear;

        if (tempMonth < 1) {
            tempMonth = 12;
            tempYear -= 1;
        }

        changeYearAndMonth(tempYear, tempMonth);
    }

    protected void onNextMonthClick(View view) {
        int tempMonth = mCurrentMonth + 1;
        int tempYear = mCurrentYear;

        if (tempMonth > 12) {
            tempMonth = 1;
            tempYear += 1;
        }

        changeYearAndMonth(tempYear, tempMonth);
    }

    public void changeYearAndMonth(int year, int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month.");
        }

        mCurrentYear = year;
        mCurrentMonth = month;

        mCustomCalendarAdapter.refreshData(mCalendarGenerator.generateCalendar(mCurrentYear, mCurrentMonth,
                new CalendarDateColorConfig(mSundayColor, mSaturdayColor, mWeekdayColor)));

        setCurrentYearAndMonth(year, month);
    }

    private void setCurrentYearAndMonth(int year, int month) {
        String currentYearAndMonth = YEAR_MONTH_FORMAT
                .replace("{Y}", year + "")
                .replace("{YD}", mYearDelimiter)
                .replace("{M}", month + "")
                .replace("{MD}", mMonthDelimiter);

        mCurrentYearAndMonthTextView.setText(currentYearAndMonth);
    }

    public String getCurrentYearAndMonth() {
        return mCurrentYearAndMonthTextView.getText().toString();
    }


}
