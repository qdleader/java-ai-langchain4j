package com.example.javaailangchain4j.config;

import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainConfig {

    @Bean
    public OpenAiStreamingChatModel openAiStreamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("OPENAI_ACCESS_KEY")) // Replace with your OpenAI API key
                .baseUrl("https://api.deepseek.com")
                .modelName("deepseek-chat") // Specify the model, e.g., gpt-4 or gpt-3.5-turbo
                .temperature(0.8)
                .maxTokens(1000)
                .build();
    }
}