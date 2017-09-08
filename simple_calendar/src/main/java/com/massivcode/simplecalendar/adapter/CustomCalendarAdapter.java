package com.massivcode.simplecalendar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.massivcode.simplecalendar.R;
import com.massivcode.simplecalendar.configs.CalendarHeaderConfig;
import com.massivcode.simplecalendar.listeners.CalendarCallback;
import com.massivcode.simplecalendar.models.CalendarColumn;
import com.massivcode.simplecalendar.models.CalendarHeader;
import com.massivcode.simplecalendar.models.CalendarModel;
import com.massivcode.simplecalendar.models.Event;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 13:47
 */

public class CustomCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_CONTENTS = 1;

    private CalendarHeaderConfig mCalendarHeaderConfig;
    private List<CalendarModel> mData;
    private CalendarCallback mCalendarCallback;

    public CustomCalendarAdapter(CalendarHeaderConfig calendarHeaderConfig, List<CalendarModel> mData, CalendarCallback mCalendarCallback) {
        mCalendarHeaderConfig = calendarHeaderConfig;
        this.mData = mData;
        this.mCalendarCallback = mCalendarCallback;
        addHeaderItems();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof CalendarHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENTS;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        if (viewType == VIEW_TYPE_CONTENTS) {
            return new CustomCalendarViewHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false));
        } else {
            return new CustomCalendarHeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar_header, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CustomCalendarViewHolder) {
            CustomCalendarViewHolder viewHolder = (CustomCalendarViewHolder) holder;
            CalendarColumn calendarModel = (CalendarColumn) getItem(position);

            if (calendarModel != null && !calendarModel.isDummy()) {
                int date = calendarModel.getDate();
                int color = calendarModel.getColor();
                viewHolder.mDateTextView.setTextColor(color);
                viewHolder.mDateTextView.setText(date + "");

                List<Event> events = calendarModel.getEvents();

                if (events != null && events.size() > 1) {
                    Event firstEvent = events.get(0);
                    viewHolder.mFirstEventTextView.setText(firstEvent.getData());
                }
            } else {
                viewHolder.mDateTextView.setText(null);
                viewHolder.mFirstEventTextView.setText(null);
            }
        } else {
            CustomCalendarHeaderViewHolder viewHolder = (CustomCalendarHeaderViewHolder) holder;
            CalendarHeader calendarHeader = (CalendarHeader) getItem(position);

            viewHolder.mTitleTextView.setTextColor(calendarHeader.getColor());
            viewHolder.mTitleTextView.setText(calendarHeader.getTitle());
        }
    }

    public void refreshData(List<CalendarModel> data) {
        mData = data;
        addHeaderItems();
        notifyDataSetChanged();
    }

    private void addHeaderItems() {
        if (mData == null || mData.size() == 0) {
            return;
        }

        if (mCalendarHeaderConfig == null) {
            return;
        }

        List<String> titles = mCalendarHeaderConfig.getTitles();
        List<Integer> colors = mCalendarHeaderConfig.getColors();

        for (int i = 0; i < 7; i++) {
            mData.add(i, new CalendarHeader(titles.get(i), colors.get(i)));
        }

    }

    public CalendarModel getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class CustomCalendarHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;

        CustomCalendarHeaderViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }

    class CustomCalendarViewHolder extends RecyclerView.ViewHolder {
        TextView mDateTextView;

        TextView mFirstEventTextView;

        CustomCalendarViewHolder(View itemView) {
            super(itemView);

            mDateTextView = (TextView) itemView.findViewById(R.id.date_tv);
            mFirstEventTextView = (TextView) itemView.findViewById(R.id.first_event_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCalendarCallback != null) {
                        CalendarColumn calendarModel = (CalendarColumn) getItem(getAdapterPosition());

                        if (calendarModel != null && !calendarModel.isDummy()) {
                            mCalendarCallback.onDateClick(calendarModel);
                        }
                    }
                }
            });
        }
    }
}
