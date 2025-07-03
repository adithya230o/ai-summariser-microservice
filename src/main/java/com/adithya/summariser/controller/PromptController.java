package com.adithya.summariser.controller;

import com.adithya.summariser.dto.PromptRequest;
import com.adithya.summariser.service.SummaryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summarise")
public class PromptController {

    private final SummaryService summaryService;

    public PromptController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    //prompt given by user
    @PostMapping("/text")
    public ResponseEntity<String> summariseText(@RequestBody PromptRequest request) {
        String summary = summaryService.summarize(request.getPrompt());
        return ResponseEntity.ok(summary);
    }

    //prompt preset
    @PostMapping("/default")
    public ResponseEntity<String> summariseDefault() {
        String presetPrompt = "Summarise it to 2 lines";
        String summary = summaryService.summarize(presetPrompt);
        return ResponseEntity.ok(summary);
    }

}
