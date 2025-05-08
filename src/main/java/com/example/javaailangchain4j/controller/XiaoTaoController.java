package com.example.javaailangchain4j.controller;

import com.example.javaailangchain4j.assistant.XiaoTaoAgent;
import com.example.javaailangchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "小桃儿")
@RestController
@RequestMapping("/xiaotao")
public class XiaoTaoController {

    @Autowired
    private XiaoTaoAgent xiaoTaoAgent;
    @Operation(summary = "对话")
    @GetMapping("/chat")
    public String chat(@RequestParam ChatForm chatForm) {
        System.out.println(chatForm);
        return xiaoTaoAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
