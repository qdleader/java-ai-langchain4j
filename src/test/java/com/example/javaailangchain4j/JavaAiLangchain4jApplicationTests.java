package com.example.javaailangchain4j;

import com.example.javaailangchain4j.assistant.Assistant;
import com.example.javaailangchain4j.assistant.MemoryChatAssistant;
import com.example.javaailangchain4j.assistant.SeparateChatAssistant;
import com.example.javaailangchain4j.bean.ChatMessages;
import com.example.javaailangchain4j.entity.Appointment;
import com.example.javaailangchain4j.service.AppointmentService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class JavaAiLangchain4jApplicationTests {

    @Test
    void contextLoads() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你好");
        System.out.println(answer);
    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringBoot() {
       String answer =  openAiChatModel.chat("你是谁");
       System.out.println(answer);
    }

    @Test
    public void testInterface() {
       Assistant assistant = AiServices.create(Assistant.class,openAiChatModel);
       String answer = assistant.chat("你是谁");
       System.out.println(answer);
    }

    @Autowired
    private Assistant assistant;

    @Test
    public void testInterface2() {
       String answer = assistant.chat("你是谁");
       System.out.println(answer);
    }


    @Test
    public void testInterface3() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(openAiChatModel)
                .chatMemory(chatMemory)
                .build();
       String answer = assistant.chat("我是杨桃儿");
       System.out.println(answer);

       String answer2 = assistant.chat("我是谁");
       System.out.println(answer2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testInterface4() {
       String answer = memoryChatAssistant.chat("我是杨桃儿");
       System.out.println(answer);
       String answer2 = memoryChatAssistant.chat("我是谁");
       System.out.println(answer2);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testInterface5() {
       String answer = separateChatAssistant.chat(1,"我是杨桃儿");
       System.out.println(answer);
       String answer2 = separateChatAssistant.chat(1,"我是谁");
       System.out.println(answer2);

       String answer3 = separateChatAssistant.chat(2,"我是谁");
       System.out.println(answer3);
    }

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

    @Test
    void testRemoveById() {
        appointmentService.removeById(1L);
    }

}
