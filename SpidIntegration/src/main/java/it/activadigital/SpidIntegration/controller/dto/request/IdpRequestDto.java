package it.activadigital.SpidIntegration.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record   IdpRequestDto(
        @JsonProperty(value = "client_id")
        String clientId,
        String idp
) {
}
