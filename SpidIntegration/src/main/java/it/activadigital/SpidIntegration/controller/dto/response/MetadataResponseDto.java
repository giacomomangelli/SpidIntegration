package it.activadigital.SpidIntegration.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetadataResponseDto(
        String esito,
        String metadata,
        @JsonProperty(value = "msg_errore")
        String msgError
) {
}
