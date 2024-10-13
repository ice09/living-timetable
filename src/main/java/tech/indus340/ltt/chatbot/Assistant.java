package tech.indus340.ltt.chatbot;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage(
        """
        You are a helpful audio chat bot.
        Answer clearly and only add relevant information, use no fillers.
        Keep answers concise, except when asked to go into detail.
        Always use the declared functions to get the correct date and the correct day of the week.
        If asked for events, always think of querying all relevant date, both school schedules and the family calendar.
        """)
    String chat(String message);

}