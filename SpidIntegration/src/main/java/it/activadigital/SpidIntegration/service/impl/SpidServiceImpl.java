package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.model.spid.AuthRequest;
import it.activadigital.SpidIntegration.repository.AuthRequestRepository;
import it.activadigital.SpidIntegration.service.SpidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class SpidServiceImpl implements SpidService {

    @Autowired
    private AuthRequestRepository authRequestRepository;

    @Override
    public AuthRequestDto getAuthRequest(IdpRequestDto idpRequestDto) {
        ResponseEntity<AuthRequestDto> requestDto = RestClient
                .create()
                .get()
                .uri(Constant.BASE_URL.getDescription() + "/get_auth_request?client_id=" + idpRequestDto.clientId() + "&idp=" + idpRequestDto.idp())
                .retrieve()
                .toEntity(AuthRequestDto.class);
        if (requestDto.getStatusCode() != HttpStatus.OK) {
            log.error("getAuthRequest returned status code {}", requestDto.getStatusCode());
            throw new RestClientException("Error in response from auth request: " + requestDto.getStatusCode());
        }
        return requestDto.getBody();
    }

    @Override
    public void saveAuthRequest(AuthRequest authRequest) {
        log.info("saving authRequest: {}", authRequest);
        authRequestRepository.save(authRequest);
    }

}
