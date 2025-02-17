package it.activadigital.SpidIntegration.controller.dto.request;

public record AssertionRequestDto(
        String relayState,
        String samlResponse
) {
}
