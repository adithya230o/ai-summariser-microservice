package com.adithya.summariser.client;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class AIClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    public String send(String userInput){

        String fullUrl = url + "?key=" + apiKey;

        String prompt = "Summarize the following text:\n" + userInput;

        // Build JSON request body
        Map<String, Object> textMap = Map.of("text", prompt);
        Map<String, Object> partsMap = Map.of("parts", List.of(textMap));
        Map<String, Object> requestBody = Map.of("contents", List.of(partsMap));

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, entity, Map.class);

            // Parse nested response structure
            List<?> candidates = (List<?>) response.getBody().get("candidates");
            if (candidates == null || candidates.isEmpty()) return "No response from Gemini.";

            Map<?, ?> candidate = (Map<?, ?>) candidates.get(0);
            Map<?, ?> content = (Map<?, ?>) candidate.get("content");
            List<?> parts = (List<?>) content.get("parts");
            if (parts == null || parts.isEmpty()) return "Empty response from Gemini.";

            Map<?, ?> part = (Map<?, ?>) parts.get(0);
            return part.get("text").toString();

        } catch (Exception e) {
            return "Error contacting Gemini API: " + e.getMessage();
        }
    }
}