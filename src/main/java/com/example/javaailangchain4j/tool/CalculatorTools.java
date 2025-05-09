package com.example.javaailangchain4j.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {
    @Tool
    double sum(double a, double b) {
        System.out.println("加");
        return a + b;
    }

    @Tool
    double squareRoot(double x) {
        System.out.println("方根");
        return Math.sqrt(x);
    }
}
