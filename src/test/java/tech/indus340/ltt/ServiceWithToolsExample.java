package tech.indus340.ltt;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * This example is derived from the LangChain4j examples here:
 * https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/ServiceWithToolsExample.java
 */
@SpringBootTest
public class ServiceWithToolsExample {

    @Value("${openai.key}")
    private String openAIKey;

    static class Calculator {

        @Tool("Calculates the length of a string")
        int stringLength(String s) {
            System.out.println("Called stringLength with s='" + s + "'");
            return s.length();
        }

        @Tool("Calculates the sum of two numbers")
        int add(int a, int b) {
            System.out.println("Called add with a=" + a + ", b=" + b);
            return a + b;
        }

        @Tool("Calculates the square root of a number")
        double sqrt(int x) {
            System.out.println("Called sqrt with x=" + x);
            return Math.sqrt(x);
        }
    }

    interface Assistant {

        Result<String> chat(String userMessage);
    }

    @Test
    public void shouldCalculateStringsAndSqrts() throws Exception {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(openAIKey)
                .modelName(GPT_4_O_MINI)
                .strictTools(true) // https://docs.langchain4j.dev/integrations/language-models/open-ai#structured-outputs-for-tools
                //.logRequests(true)
                //.logResponses(true)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new Calculator())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        String question = """
            Was ist die Quadratwurzel der Anzahl der Zeichen
            in der Wortfolge "Das alles ist viel einfacher
            mit Function Calling"?
            """;

        Result<String> answer = assistant.chat(question);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        System.out.println("\n\n### Answer ###");
        System.out.println(answer.content());
        System.out.println("\n### Tool Executions ###");
        System.out.println(objectWriter.writeValueAsString(answer.toolExecutions()));

        // The square root of the sum of the number of letters in the words "hello" and "world" is approximately 3.162.
    }

}