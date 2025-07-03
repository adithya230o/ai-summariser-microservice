package com.adithya.summariser.client;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import java.util.*;

@Component
public class OpenAIClient {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.model}")
    private String apiModel;

    @Value("${openai.api.url}")
    private String apiUrl;

    //Sends a prompt to the OpenAI chat completion endpoint and returns the assistant's reply.
    public String send(String userInput){

        RestTemplate restTemplate = new RestTemplate();

        // Construct user message as per OpenAI's Chat API format
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", userInput);

        // Build the full request payload
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", apiModel);
        requestBody.put("messages", List.of(userMessage));

        // Set HTTP headers including content type and bearer auth
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Send the POST request to OpenAI API
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<>() {}
            );

            // Extract assistant response from the first choice
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

            return (String) message.get("content");

        } catch (Exception e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}

