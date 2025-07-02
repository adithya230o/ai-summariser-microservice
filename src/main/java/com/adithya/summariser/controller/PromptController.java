package com.adithya.summariser.controller;

import com.adithya.summariser.dto.PromptRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summarise")
public class PromptController {

    @PostMapping("/text")
    public ResponseEntity<String> summariseText(@RequestBody PromptRequest request) {
        String summary = "Summary generated for the prompt : " + request.getPrompt();
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/default")
    public ResponseEntity<String> summariseDefault() {
        String precodedPrompt = "Summarise it to 2 lines";
        String summary = "Summary generated for precoded prompt";
        return ResponseEntity.ok(summary);
    }

}
