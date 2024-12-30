package it.activadigital.SpidIntegration.controller.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestError(
        String esito,
        @JsonProperty(value = "msg_errore")
        String msgErrore
) {
}
