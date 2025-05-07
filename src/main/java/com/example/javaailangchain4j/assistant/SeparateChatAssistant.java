package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "openAiChatModel",chatMemoryProvider = "chatMemoryProvider")
public interface SeparateChatAssistant {
    @SystemMessage(fromResource = "my-prompt-template.txt")
    String chat(@MemoryId int memoryId,@UserMessage String userMessage);

    @UserMessage("你是我的好朋友，请用英语回答问题，{{message}}")
    String chat2(@MemoryId int memoryId,@V("message") String userMessage);
}
