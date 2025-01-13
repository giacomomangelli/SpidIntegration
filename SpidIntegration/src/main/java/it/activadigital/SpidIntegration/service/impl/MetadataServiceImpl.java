package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.MetadataService;
import it.activadigital.SpidIntegration.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private RequestUtil util;

    @Override
    public MetadataResponseDto getSpidMetadata(String clientId) {
        log.info("getSpidMetadata client id {}", clientId);
        ResponseEntity<MetadataResponseDto> responseDto = RestClient
                .create()
                .get()
                .uri(Constant.SPID_BASE_URL.getDescription() + "/get_metadata?client_id=" + clientId)
                .headers(httpHeaders -> {
                    httpHeaders.addAll(util.setHeaders());
                })
                .retrieve()
                .toEntity(MetadataResponseDto.class);
        if (responseDto.getStatusCode() != HttpStatus.OK) {
            log.error("get Spid Metadata returned status code {}", responseDto.getStatusCode());
            throw new RestClientException("Error in response from metadata request: " + responseDto.getStatusCode());
        }
        return responseDto.getBody();
    }

    @Override
    public MetadataResponseDto getCieMetadata(String clientId) {
        log.info("getCieMetadata client id {}", clientId);
        ResponseEntity<MetadataResponseDto> responseDto = RestClient
                .create()
                .get()
                .uri(Constant.CIE_BASE_URL.getDescription() + "/get_metadata?client_id=" + clientId)
                .retrieve()
                .toEntity(MetadataResponseDto.class);
        if (responseDto.getStatusCode() != HttpStatus.OK) {
            log.error("get Cie Metadata returned status code {}", responseDto.getStatusCode());
            throw new RestClientException("Error in response from metadata request: " + responseDto.getStatusCode());
        }
        return responseDto.getBody();
    }

}
