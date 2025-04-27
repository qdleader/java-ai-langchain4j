package com.example.javaailangchain4j;

import com.example.javaailangchain4j.assistant.Assistant;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaAiLangchain4jApplicationTests {

    @Test
    void contextLoads() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你好");
        System.out.println(answer);
    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringBoot() {
       String answer =  openAiChatModel.chat("你是谁");
       System.out.println(answer);
    }

    @Test
    public void testInterface() {
       Assistant assistant = AiServices.create(Assistant.class,openAiChatModel);
       String answer = assistant.chat("你是谁");
       System.out.println(answer);
    }

    @Autowired
    private Assistant assistant;

    @Test
    public void testInterface2() {
       String answer = assistant.chat("你是谁");
       System.out.println(answer);
    }

}
