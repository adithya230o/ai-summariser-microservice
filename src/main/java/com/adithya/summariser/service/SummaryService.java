package com.adithya.summariser.service;

import com.adithya.summariser.client.*;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {
    private final OpenAIClient openAIClient;

    public SummaryService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String summarize(String inputText) {
        return openAIClient.send(inputText);
    }
}
