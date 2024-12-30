package it.activadigital.SpidIntegration.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AssertionRequestDto(
        @JsonProperty(value = "client_id")
        String clientId,
        String assertion
) {
}
