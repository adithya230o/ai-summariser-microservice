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
        String presetPrompt = "Give an apt heading for this line : 'The Industrial Revolution was a period of major technological advancement, primarily in the late 18th and 19th centuries, that transformed agrarian and handicraft economies into ones dominated by industry and machine manufacturing. '";
        String summary = summaryService.summarize(presetPrompt);
        return ResponseEntity.ok(summary);
    }

}
