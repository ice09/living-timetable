package tech.indus340.ltt.calendar;

import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/* class to demonstrate use of Calendar events list API */
@Service
public class SampleCalendarService {

    public List<CalendarEntry> getNextCalendarEvents() {
        List<CalendarEntry> events = new ArrayList<>();

        // Fußballtraining on Monday (Montag)
        events.add(createEvent("Fußballtraining", DayOfWeek.MONDAY, 17, 0, 19, 0));

        // Reitstunde on Tuesday (Dienstag)
        events.add(createEvent("Reitstunde", DayOfWeek.TUESDAY, 15, 0, 17, 0));

        // Tanzunterricht on Wednesday (Mittwoch)
        events.add(createEvent("Tanzunterricht", DayOfWeek.WEDNESDAY, 17, 0, 19, 0));

        // Klavierunterricht on Thursday (Donnerstag)
        events.add(createEvent("Klavierunterricht", DayOfWeek.THURSDAY, 15, 0, 16, 0));

        // Fußballspiel on Friday (Freitag)
        events.add(createEvent("Fußballspiel (Heimspiel)", DayOfWeek.FRIDAY, 10, 0, 13, 0));

        // Elternabend on Friday (Freitag)
        events.add(createEvent("Elternabend in der Schule", DayOfWeek.FRIDAY, 19, 0, 22, 0));

        // Zahnarzttermin on Saturday (Samstag)
        events.add(createEvent("Zahnarzttermin", DayOfWeek.SATURDAY, 9, 0, 10, 0));

        // Familienbrunch on Sunday (Sonntag)
        events.add(createEvent("Familienbrunch bei Oma und Opa", DayOfWeek.SUNDAY, 11, 0, 14, 0));

        return events;
    }

    private CalendarEntry createEvent(String description, DayOfWeek nextDayOfWeek, int starttimehour, int starttimeminute, int endtimehour, int endtimeminute) {
        LocalDate nextDayOfWeekLocalDate = LocalDate.now().with(TemporalAdjusters.next(nextDayOfWeek));
        return new CalendarEntry(
                description,
                LocalDateTime.of(nextDayOfWeekLocalDate, LocalTime.of(starttimehour, starttimeminute)),
                LocalDateTime.of(nextDayOfWeekLocalDate, LocalTime.of(endtimehour, endtimeminute))
        );
    }

}