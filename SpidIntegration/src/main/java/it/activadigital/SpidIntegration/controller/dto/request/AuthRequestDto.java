package it.activadigital.SpidIntegration.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthRequestDto(
        String esito,
        @JsonProperty(value = "issue_istant")
        String issueIstant,
        String uuid,
        @JsonProperty(value = "b64_request_comp")
        String b64RequestComp,
        @JsonProperty(value = "sso_request")
        String ssoRequest,
        @JsonProperty(value = "msg_errore")
        String msgError
) {
}
