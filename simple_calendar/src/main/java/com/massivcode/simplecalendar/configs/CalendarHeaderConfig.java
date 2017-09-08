package com.massivcode.simplecalendar.configs;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 7. 14:55
 */

public class CalendarHeaderConfig {
    private List<String> titles;
    private List<Integer> colors;

    public CalendarHeaderConfig(List<String> titles, List<Integer> colors) {
        this.titles = titles;
        this.colors = colors;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<Integer> getColors() {
        return colors;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CalendarHeaderConfig{");
        sb.append("titles=").append(titles);
        sb.append(", colors=").append(colors);
        sb.append('}');
        return sb.toString();
    }
}
