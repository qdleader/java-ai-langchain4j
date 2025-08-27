package com.example.javaailangchain4j;

import com.example.javaailangchain4j.assistant.Assistant;
import com.example.javaailangchain4j.assistant.MemoryChatAssistant;
import com.example.javaailangchain4j.assistant.SeparateChatAssistant;
import com.example.javaailangchain4j.bean.ChatMessages;
import com.example.javaailangchain4j.entity.Appointment;
import com.example.javaailangchain4j.service.AppointmentService;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.store.embedding.*;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class JavaAiLangchain4jApplicationTests {

//    @Test
//    void contextLoads() {
//        OpenAiChatModel model = OpenAiChatModel.builder()
//                .baseUrl("http://langchain4j.dev/demo/openai/v1")
//                .apiKey("demo")
//                .modelName("gpt-4o-mini")
//                .build();
//
//        String answer = model.chat("你好");
//        System.out.println(answer);
//    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

//    @Test
//    public void testSpringBoot() {
//       String answer =  openAiChatModel.chat("你是谁");
//       System.out.println(answer);
//    }

//    @Test
//    public void testInterface() {
//       Assistant assistant = AiServices.create(Assistant.class,openAiChatModel);
//       String answer = assistant.chat("你是谁");
//       System.out.println(answer);
//    }

    @Autowired
    private Assistant assistant;

//    @Test
//    public void testInterface2() {
//       String answer = assistant.chat("你是谁");
//       System.out.println(answer);
//    }


//    @Test
//    public void testInterface3() {
//        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
//
//        Assistant assistant = AiServices
//                .builder(Assistant.class)
//                .chatLanguageModel(openAiChatModel)
//                .chatMemory(chatMemory)
//                .build();
//       String answer = assistant.chat("我是杨桃儿");
//       System.out.println(answer);
//
//       String answer2 = assistant.chat("我是谁");
//       System.out.println(answer2);
//    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
//    @Test
//    public void testInterface4() {
//       String answer = memoryChatAssistant.chat("我是杨桃儿");
//       System.out.println(answer);
//       String answer2 = memoryChatAssistant.chat("我是谁");
//       System.out.println(answer2);
//    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
//    @Test
//    public void testInterface5() {
//       String answer = separateChatAssistant.chat(1,"我是杨桃儿");
//       System.out.println(answer);
//       String answer2 = separateChatAssistant.chat(1,"我是谁");
//       System.out.println(answer2);
//
//       String answer3 = separateChatAssistant.chat(2,"我是谁");
//       System.out.println(answer3);
//    }

    @Test
    public void testInterface6() {
       String answer = separateChatAssistant.chat(1,"今天是几号？");
       System.out.println(answer);
    }

    @Test
    public void testInterface7() {
       String answer = separateChatAssistant.chat2(1,"1+2等于几，9的平方根是多少？");
       System.out.println(answer);
    }

//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Test
//    public void testMongo() {
//        mongoTemplate.insert(new ChatMessages(1L,"我是杨桃儿"));
//       System.out.println(mongoTemplate);
//    }

    @Autowired
    private AppointmentService appointmentService;
    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("内科");
        appointment.setDate("2025-04-14");
        appointment.setTime("上午");
        Appointment appointmentDB = appointmentService.getOne(appointment);
        System.out.println(appointmentDB);
    }
    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("内科");
        appointment.setDate("2025-07-14");
        appointment.setTime("上午");
        appointment.setDoctorName("张医生");
        appointmentService.save(appointment);
    }

//    @Test
//    void testRemoveById() {
//        appointmentService.removeById(1L);
//    }
//
//    @Test
//    public void testReadDocument() {
//
//        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/科室信息.md");
//                System.out.println(document.text());
//    }

//    import dev.langchain4j.model.dashscope.QwenEmbeddingModel;




//    @Test
//    public void testEmbeddingModel(){
//        EmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
//                .apiKey(System.getenv("DASH_ACCESS_KEY"))
//                .modelName("text-embedding-v3")
//                .build();
//
//        Response<Embedding> embed = embeddingModel.embed("你好");
//        System.out.println("向量维度：" + embed.content().vector().length);
//        System.out.println("向量输出：" + embed.toString());
//    }
//
//    @Autowired
//    private EmbeddingStore embeddingStore;
    /**
     * 将文本转换成向量，然后存储到pinecone中
     *
     * 参考：
     * https://docs.langchain4j.dev/tutorials/embedding-stores
     */
//    @Test
//    public void testPineconeEmbeded() {
//        EmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
//                .apiKey(System.getenv("DASH_ACCESS_KEY"))
//                .modelName("text-embedding-v3")
//                .build();
//
//        //将文本转换成向量
//        TextSegment segment1 = TextSegment.from("我喜欢乒乓球");
//        Embedding embedding1 = embeddingModel.embed(segment1).content();
//            //存入向量数据库
//        embeddingStore.add(embedding1, segment1);
//        TextSegment segment2 = TextSegment.from("今天天气很好");
//        Embedding embedding2 = embeddingModel.embed(segment2).content();
//        embeddingStore.add(embedding2, segment2);
//    }

    /**
    * Pinecone-相似度匹配
    */
//    @Test
//    public void embeddingSearch() {
//
//        EmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
//                .apiKey(System.getenv("DASH_ACCESS_KEY"))
//                .modelName("text-embedding-v3")
//                .build();
//
//        //提问，并将问题转成向量数据
//        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么？").content();
//        //创建搜索请求对象
//        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
//                .queryEmbedding(queryEmbedding)
//                .maxResults(1) //匹配最相似的一条记录
//                //.minScore(0.8)
//                .build();
//
//        //根据搜索请求 searchRequest 在向量存储中进行相似度搜索
//        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
//        //searchResult.matches()：获取搜索结果中的匹配项列表。
//        //.get(0)：从匹配项列表中获取第一个匹配项
//        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
//        //获取匹配项的相似度得分
//        System.out.println(embeddingMatch.score()); // 0.8144288515898701
//        //返回文本结果
//        System.out.println(embeddingMatch.embedded().text());
//    }

//    @Test
//    public void testUploadKnowledgeLibrary() {
//        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
//                EmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
//                        .apiKey(System.getenv("DASH_ACCESS_KEY"))
//                        .modelName("text-embedding-v3")
//                        .build();
//        //并使用默认的文档解析器对文档进行解析
//            Document document1 =  FileSystemDocumentLoader.loadDocument("src/main/resources/医院信息.md");
//            Document document2 = FileSystemDocumentLoader.loadDocument("src/main/resources/科室信息.md");
//            Document document3 = FileSystemDocumentLoader.loadDocument("src/main/resources/呼吸内科信息.md");
//            List<Document> documents = Arrays.asList(document1, document2,document3);
//        //文本向量化并存入向量数据库：将每个片段进行向量化，得到一个嵌入向量
//        EmbeddingStoreIngestor
//                .builder()
//                .embeddingStore(embeddingStore)
//                .embeddingModel(embeddingModel)
//                .build()
//                .ingest(documents);
//    }

}
