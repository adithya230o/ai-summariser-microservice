package com.adithya.aicore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for encapsulating user input prompts sent to the AI microservice.
 */
@Setter
@Getter
public class PromptRequest {

    /**
     * The natural language prompt provided by the user.
     */
    private String prompt;

}
