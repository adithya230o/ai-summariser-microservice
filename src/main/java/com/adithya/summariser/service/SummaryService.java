package com.adithya.summariser.service;

import com.adithya.summariser.client.*;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {
    private final OpenAIHelper openAIHelper;

    public SummaryService(OpenAIHelper openAIHelper) {
        this.openAIHelper = openAIHelper;
    }

    public String summarize(String inputText) {
        return openAIHelper.send(inputText);
    }
}
