package com.example.javaailangchain4j.controller;

import com.example.javaailangchain4j.assistant.XiaoTaoAgent;
import com.example.javaailangchain4j.assistant.XiaoTaoAgentFlux;
import com.example.javaailangchain4j.bean.ChatForm;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Tag(name = "小桃儿")
@RestController
@RequestMapping("/xiaotao")
public class XiaoTaoController {

    @Autowired
    private XiaoTaoAgent xiaoTaoAgent;
    @Operation(summary = "对话")
    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "1") Long memoryId, @RequestParam String message) {
        System.out.println(memoryId);
        return xiaoTaoAgent.chat(memoryId, message);
    }

    @Operation(summary = "对话")
    @PostMapping("/chatPost")
    public String chat1(@RequestBody ChatForm chatForm) {
        System.out.println(chatForm);
        return xiaoTaoAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }

    @Autowired
    private XiaoTaoAgentFlux xiaoTaoAgentFlux;

    @Operation(summary = "流式对话")
    @PostMapping(value = "/chatPostFlux",  produces = "text/stream;charset=utf-8")
    public Flux<String> chat2(@RequestBody ChatForm chatForm) {
        System.out.println(chatForm);
        return xiaoTaoAgentFlux.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }

}
