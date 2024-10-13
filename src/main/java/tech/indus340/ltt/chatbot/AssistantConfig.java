package tech.indus340.ltt.chatbot;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.indus340.ltt.calendar.CalendarAssistant;
import tech.indus340.ltt.schoolschedule.RecurringSchoolScheduleAssistant;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.time.Duration.ofSeconds;

@Configuration
public class AssistantConfig {

    private final static int KONTEXT_WINDOW_MESSAGES_SIZE = 10;

    @Value("${openai.key}")
    private String openAIKey;

    @Bean
    public ChatLanguageModel model() {
        return OpenAiChatModel.builder()
                .apiKey(openAIKey)
                .modelName(GPT_4_O_MINI)
                .temperature(0d)
                .strictTools(true)
                .timeout(ofSeconds(60))
                .build();
    }

    @Bean
    public Assistant assistant(ChatLanguageModel model, CalendarAssistant calendarAssistant, RecurringSchoolScheduleAssistant recurringSchoolScheduleAssistant) {
        ChatMemory chatMemory =
                MessageWindowChatMemory
                        .withMaxMessages(KONTEXT_WINDOW_MESSAGES_SIZE);
        return AiServices.builder(Assistant.class)
                .chatMemory(chatMemory)
                .chatLanguageModel(model)
                .tools(calendarAssistant, recurringSchoolScheduleAssistant)
                .build();
    }
}
