package com.example.javaailangchain4j.config;

import com.example.javaailangchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class XiaoTaoAgentConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    ChatMemoryProvider chatMemoryProviderXiaoTao() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    @Bean
    ContentRetriever contentRetrieverXiaoTao() {
        Document document1 =  FileSystemDocumentLoader.loadDocument("src/main/resources/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("src/main/resources/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("src/main/resources/呼吸内科信息.md");
        List<Document> documents = Arrays.asList(document1, document2,document3);

        // 使用内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore();
        // 使用默认文档分割器
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        // 从嵌入存储(EmbeddingStore)中检索内容
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
