package com.adithya.aicore.service;

import com.adithya.aicore.client.*;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final AIClient aiClient;

    public AIService(AIClient aiClient) {
        this.aiClient = aiClient;
    }

    public String handlePrompt(String inputPrompt) {
        return aiClient.send(inputPrompt);
    }
}
