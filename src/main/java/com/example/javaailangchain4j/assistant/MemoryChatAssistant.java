package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;


@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "openAiChatModel",chatMemory = "chatMemory")
public interface MemoryChatAssistant {
    @UserMessage("不是我的好朋友，请用广东话回答问题，并且添加一些表情符号。{{message}}")
    String chat(@V("message") String message);
}
