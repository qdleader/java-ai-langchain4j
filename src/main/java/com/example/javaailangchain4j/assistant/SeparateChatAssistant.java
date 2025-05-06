package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "openAiChatModel",chatMemoryProvider = "chatMemoryProvider")
public interface SeparateChatAssistant {
    @SystemMessage("你是我的好朋友，请用东北话回答问题")
    String chat(@MemoryId int memoryId,@UserMessage String userMessage);
}
