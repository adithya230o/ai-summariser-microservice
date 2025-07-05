package com.adithya.aicore.service;

import com.adithya.aicore.client.*;
import org.springframework.stereotype.Service;

/**
 *Handles user-provided prompt,or,mock request, and forwards it to the AI client.
 */
@Service
public class AIService {
    private final AIClient aiClient;

    public AIService(AIClient aiClient) {
        this.aiClient = aiClient;
    }


    /**
     * Handles user-provided prompt and forwards it to the AI client.
     * @param inputPrompt The prompt text to be processed by the AI
     * @return The AI-generated response as a string
     */
    public String handlePrompt(String inputPrompt) {
        return aiClient.send(inputPrompt);
    }

    /**
     * Handles mock response generation for load testing purposes.
     * @return A fixed mock response string
     */
    public String getMockResponse() {
        return aiClient.getMockResponse();
    }
}
