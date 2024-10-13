package tech.indus340.ltt.calendar;

import java.time.LocalDateTime;

public record CalendarEntry(String description, LocalDateTime startTime, LocalDateTime endTime) {

    boolean isAllDayEvent() {
        return startTime == null;
    }

}
