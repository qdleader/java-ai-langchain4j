package com.example.javaailangchain4j.config;

import com.example.javaailangchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class XiaoTaoAgentConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Autowired
    private EmbeddingStore embeddingStore;


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
        return null;

//
//        Document document1 =  FileSystemDocumentLoader.loadDocument("src/main/resources/医院信息.md");
//        Document document2 = FileSystemDocumentLoader.loadDocument("src/main/resources/科室信息.md");
//        Document document3 = FileSystemDocumentLoader.loadDocument("src/main/resources/呼吸内科信息.md");
//        List<Document> documents = Arrays.asList(document1, document2,document3);
//
//        // 使用内存向量存储
//        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore();
//        // 使用默认文档分割器
//        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
//
//        // 从嵌入存储(EmbeddingStore)中检索内容
//        return EmbeddingStoreContentRetriever.from(embeddingStore);
        // 使用Spring的资源加载器来获取文件
//        Resource resource1 = new ClassPathResource("src/main/resources/医院信息.md");
//        Resource resource2 = new ClassPathResource("src/main/resources/科室信息.md");
//        Resource resource3 = new ClassPathResource("src/main/resources/呼吸内科信息.md");
//
//        try {
//            Document document1 = FileSystemDocumentLoader.loadDocument(resource1.getFile().toPath());
//            Document document2 = FileSystemDocumentLoader.loadDocument(resource2.getFile().toPath());
//            Document document3 = FileSystemDocumentLoader.loadDocument(resource3.getFile().toPath());
//
//            List<Document> documents = Arrays.asList(document1, document2, document3);
//
//            // 使用内存向量存储
//            InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
//            // 使用默认文档分割器
//            EmbeddingStoreIngestor.ingest(documents, embeddingStore);
//
//            // 从嵌入存储(EmbeddingStore)中检索内容
//            return EmbeddingStoreContentRetriever.from(embeddingStore);
//        } catch (IOException e) {
//            throw new RuntimeException("无法加载资源文件", e);
//        }
    }


    @Bean
    ContentRetriever contentRetrieverXiaoTaoPinecone() {
        EmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
                .apiKey(System.getenv("DASH_ACCESS_KEY"))
                .modelName("text-embedding-v3")
                .build();
        return EmbeddingStoreContentRetriever
                .builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(1) //  设置最大检索结果数量，这里表示最多返回 1 条匹配
                .minScore(0.8) // // 设置最小得分阈值，只有得分大于等于 0.8 的结果才会被返回
                .build();
    }



}
