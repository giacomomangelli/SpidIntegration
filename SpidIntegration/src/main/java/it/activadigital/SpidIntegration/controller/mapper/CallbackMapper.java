package it.activadigital.SpidIntegration.controller.mapper;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallbackMapper {

    public static AssertionRequestDto mapToEntryDto(String callbackResponse) {
        int firstIndexState = callbackResponse.indexOf("=");
        int lastIndexState = callbackResponse.indexOf("&");
        String relayState = callbackResponse.substring(firstIndexState + 1, lastIndexState);
        int firstIndexSaml = callbackResponse.indexOf("=", lastIndexState);
        String samlResponse = callbackResponse.substring(firstIndexSaml + 1);
        return new AssertionRequestDto(relayState, samlResponse);
    }
}
