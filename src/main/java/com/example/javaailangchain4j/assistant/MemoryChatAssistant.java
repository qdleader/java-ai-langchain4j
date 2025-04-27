package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "openAiChatModel",chatMemory = "chatMemory")
public interface MemoryChatAssistant {
    String chat(String message);
}
