package com.adithya.summariser.client;

import org.springframework.stereotype.Component;

@Component
public class OpenAIHelper {
    public String send(String userInput){
        return "SUMMARY";
    }
}
