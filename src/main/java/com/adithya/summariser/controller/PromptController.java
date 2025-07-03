package com.adithya.summariser.controller;

import com.adithya.summariser.dto.PromptRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summarise")
public class PromptController {

    //prompt given by user
    @PostMapping("/text")
    public ResponseEntity<String> summariseText(@RequestBody PromptRequest request) {
        String summary = "Summary generated for the prompt : " + request.getPrompt();
        return ResponseEntity.ok(summary);
    }

    //prompt preset
    @PostMapping("/default")
    public ResponseEntity<String> summariseDefault() {
        String presetPrompt = "Summarise it to 2 lines";
        String summary = "Summary generated for preset prompt";
        return ResponseEntity.ok(summary);
    }

}
