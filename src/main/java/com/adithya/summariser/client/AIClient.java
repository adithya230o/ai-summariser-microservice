package com.adithya.summariser.client;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import java.util.*;

@Component
public class OpenAIClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    @Value("${gemini.api.url}")
    private String apiUrl;

    //Sends a prompt to the OpenAI chat completion endpoint and returns the assistant's reply.
    public String send(String userInput){

        private final RestTemplate restTemplate = new RestTemplate();

        public String getSummary(String text) {
            String fullUrl = String.format("%s/models/%s:generateContent?key=%s", apiUrl, model, apiKey);
            String prompt = "Summarize the following text:\n" + inputText;

            // Request Body
            Map<String, Object> textMap = Map.of("text", prompt);
            Map<String, Object> partsMap = Map.of("parts", List.of(textMap));
            Map<String, Object> requestBody = Map.of("contents", List.of(partsMap));

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            try {
                ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, entity, Map.class);

                // Parse response
                List candidates = (List) response.getBody().get("candidates");
                Map candidate = (Map) candidates.get(0);
                Map content = (Map) candidate.get("content");
                List parts = (List) content.get("parts");
                Map part = (Map) parts.get(0);
                return part.get("text").toString();

            } catch (Exception e) {
                return "Error contacting Gemini API: " + e.getMessage();
            }
        }
    }