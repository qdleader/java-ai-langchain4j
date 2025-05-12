package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "openAiChatModel",chatMemoryProvider = "chatMemoryProviderXiaoTao",tools="appointmentTools")
public interface XiaoTaoAgent {
    @SystemMessage(fromResource = "xiaotao-prompt-template.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
