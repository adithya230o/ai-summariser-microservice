package com.adithya.aicore.controller;

import com.adithya.aicore.dto.PromptRequest;
import com.adithya.aicore.service.AIService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user-defined and preset AI prompts via Gemini.
 */

@RestController
@RequestMapping("/api/prompt")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    //prompt given by user
    @PostMapping
    public ResponseEntity<String> postPrompt(@RequestBody PromptRequest request) {
        String summary = aiService.handlePrompt(request.getPrompt());
        return ResponseEntity.ok(summary);
    }

    //prompt preset
    @PostMapping("/preset")
    public ResponseEntity<String> postPresetPrompt() {
        String presetPrompt = "Give 2-3 lines about java.";

        String promptResponse = aiService.handlePrompt(presetPrompt);
        return ResponseEntity.ok(promptResponse);
    }

}
