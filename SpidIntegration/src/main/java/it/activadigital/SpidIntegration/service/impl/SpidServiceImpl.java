package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.service.SpidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SpidServiceImpl implements SpidService {

    @Override
    public void getAuthRequest(IdpRequestDto idpRequestDto) {
        RestTemplate restTemplate = new RestTemplate();

    }

}
