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
public class InfoTracciaturaDto {

    private String response;
    @JsonProperty(value = "response_id")
    private String responseId;
    @JsonProperty(value = "response_issue_instant")
    private String responseIssueInstant;
    @JsonProperty(value = "response_issuer")
    private String responseIssuer;
    @JsonProperty(value = "assertion_id")
    private String assertionId;
    @JsonProperty(value = "assertion_subject")
    private String assertionSubject;
    @JsonProperty(value = "assertion_subject_name_qualifier")
    private String assertionSubjectNameQualifier;


}


