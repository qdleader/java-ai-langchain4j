package com.example.javaailangchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel  = "openAiStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoTao",
        tools="appointmentTools",
        contentRetriever = "contentRetrieverXiaoTaoPinecone"
)
public interface XiaoTaoAgentFlux {
    @SystemMessage(fromResource = "xiaotao-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
