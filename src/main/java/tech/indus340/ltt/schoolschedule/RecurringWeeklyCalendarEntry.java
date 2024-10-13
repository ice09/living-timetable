package tech.indus340.ltt.schoolschedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class RecurringWeeklyCalendarEntry {
    private final String description;

    private final DayOfWeek day;
    private final LocalTime start;
    private final LocalTime end;
    public RecurringWeeklyCalendarEntry(String description, DayOfWeek day, LocalTime start, LocalTime end) {
        this.description = description;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Class: " + description + ", Day: " + day + ", Start: " + start + ", End: " + end;
    }
}
