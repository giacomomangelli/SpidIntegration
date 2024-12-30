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
public class AssertionCieResponse {

    private String esito;
    @JsonProperty(value = "attributi_utente")
    private AttributiUtenteCieDto attributiUtente;
    @JsonProperty(value = "response_id")
    private String responseId;
    @JsonProperty(value = "info_tracciatura")
    private InfoTracciaturaDto infoTracciatura;
}
