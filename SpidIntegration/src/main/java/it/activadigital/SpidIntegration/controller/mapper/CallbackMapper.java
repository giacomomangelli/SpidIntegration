package it.activadigital.SpidIntegration.controller.mapper;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;

@Slf4j
public class CallbackMapper {

    public static AssertionRequestDto mapToEntryDto(MultiValueMap<String, String> callbackMap) {
        String relayState = callbackMap.getFirst("RelayState");
        String samlResponse = callbackMap.getFirst("SAMLResponse");
        return new AssertionRequestDto(relayState, samlResponse);
    }
}
