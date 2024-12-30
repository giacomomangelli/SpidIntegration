package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class MetadataServiceImpl implements MetadataService {

    @Override
    public MetadataResponseDto getMetadata(String clientId) {
        ResponseEntity<MetadataResponseDto> responseDto = RestClient
                .create()
                .get()
                .uri(Constant.BASE_URL.getDescription() + "/get_metadata?client_id=" + clientId)
                .retrieve()
                .toEntity(MetadataResponseDto.class);
        if (responseDto.getStatusCode() != HttpStatus.OK) {
            log.error("getMetadata returned status code {}", responseDto.getStatusCode());
            throw new RestClientException("Error in response from metadata request: " + responseDto.getStatusCode());
        }
        return responseDto.getBody();
    }
}