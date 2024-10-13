package tech.indus340.ltt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.indus340.ltt.chatbot.Assistant;

@RestController
public class TimeTableController {

    private final Assistant assistant;

    public TimeTableController(Assistant assistant) {
        this.assistant = assistant;
    }

    @GetMapping("/timetable")
    public ResponseEntity<String> queryTimeTable(@RequestParam("message") String message) {
        return ResponseEntity.ofNullable(assistant.chat(message));
    }
}
