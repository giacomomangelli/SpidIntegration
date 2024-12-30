package it.activadigital.SpidIntegration.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssertionSpidResponse {

    private String esito;
    @JsonProperty(value = "provider_id")
    private String providerId;
    @JsonProperty(value = "attributi_utente")
    private AttributiUtenteSpidDto attributiUtente;
    @JsonProperty(value = "response_id")
    private String responseId;
    @JsonProperty(value = "info_tracciatura")
    private InfoTracciaturaDto infoTracciatura;

}