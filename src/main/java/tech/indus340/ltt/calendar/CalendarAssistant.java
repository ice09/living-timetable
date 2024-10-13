package tech.indus340.ltt.calendar;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalendarAssistant {

    private final SampleCalendarService sampleCalendarService;

    public CalendarAssistant(SampleCalendarService sampleCalendarService) {
        this.sampleCalendarService = sampleCalendarService;
    }


    @Tool("Returns next events from family calendar")
    public List<CalendarEntry> getNextEvents()  {
        try {
            return sampleCalendarService.getNextCalendarEvents();
        } catch (Exception ex) {
            return List.of();
        }
    }

    @Tool("Returns today's date, use whenever today's date is needed")
    public LocalDate getCurrentDate() {
        System.out.println("getCurrentDate was called");
        return LocalDate.now();
    }

}
