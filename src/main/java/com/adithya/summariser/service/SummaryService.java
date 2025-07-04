package com.adithya.summariser.service;

import com.adithya.summariser.client.*;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {
    private final AIClient aiClient;

    public SummaryService(AIClient aiClient) {
        this.aiClient = aiClient;
    }

    public String summarize(String inputText) {
        return aiClient.send(inputText);
    }
}
