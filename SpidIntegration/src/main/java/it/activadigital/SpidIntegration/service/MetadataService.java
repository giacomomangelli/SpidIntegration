package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;

public interface MetadataService {

    MetadataResponseDto getMetadata(String clientId);

}
