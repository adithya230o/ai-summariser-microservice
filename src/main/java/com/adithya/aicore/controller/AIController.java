package com.adithya.aicore.controller;

import com.adithya.aicore.dto.PromptRequest;
import com.adithya.aicore.service.AIService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling user-defined and preset AI prompts via Gemini.
 */

@RestController
@RequestMapping("/api/prompt")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }


    /**
     * Handles a user-specified prompt.
     * @param request PromptRequest containing the natural language prompt
     * @return ResponseEntity with AI-generated output
     */
    @PostMapping
    public ResponseEntity<String> postPrompt(@RequestBody PromptRequest request) {
        String summary = aiService.handlePrompt(request.getPrompt());
        return ResponseEntity.ok(summary);
    }



    /**
     * Handles a hardcoded preset prompt.
     * @return ResponseEntity with AI-generated response to the preset prompt
     */
    @PostMapping("/preset")
    public ResponseEntity<String> postPresetPrompt() {
        String presetPrompt = "Give 2-3 lines about java.";

        String promptResponse = aiService.handlePrompt(presetPrompt);
        return ResponseEntity.ok(promptResponse);
    }


    /**
     * Simulates a backend load test by generating a mock request without calling the external AI API
     * @return ResponseEntity with a static mock message
     */
    @PostMapping("/load-test")
    public ResponseEntity<String> handleLoadTest() {
        return ResponseEntity.ok(aiService.getMockResponse());
    }
}
