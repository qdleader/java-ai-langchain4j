package com.example.javaailangchain4j.controller;

import com.example.javaailangchain4j.assistant.XiaoTaoAgent;
import com.example.javaailangchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
