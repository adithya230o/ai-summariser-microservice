package com.adithya.aicore.client;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Generic AI client to communicate with Gemini 2.0 Flash using REST API.
 */
@Component
public class AIClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Sends a prompt to Gemini API and returns the generated text response.
     * @param inputPrompt The natural language prompt to be processed by Gemini
     * @return The AI-generated text or an error/fallback message
     */
    public String send(String inputPrompt){

        String fullUrl = url + "?key=" + apiKey;

        String prompt = inputPrompt;

        // Construct the JSON request body according to Gemini's expected format
        Map<String, Object> textMap = Map.of("text", prompt);
        Map<String, Object> partsMap = Map.of("parts", List.of(textMap));
        Map<String, Object> requestBody = Map.of("contents", List.of(partsMap));

        // Set appropriate headers for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Send the POST request to Gemini
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

    /**
     * Returns a static mock response to simulate AI interaction.
     * Used during load testing to bypass real API calls and measure backend scalability.
     * @return A fixed string indicating load test success
     */
    public String getMockResponse() {
        return "Load test response received.";
    }

}