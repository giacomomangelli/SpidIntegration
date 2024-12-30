package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;

public interface MetadataService {

    MetadataResponseDto getSpidMetadata(String clientId);
    MetadataResponseDto getCieMetadata(String clientId);

}
