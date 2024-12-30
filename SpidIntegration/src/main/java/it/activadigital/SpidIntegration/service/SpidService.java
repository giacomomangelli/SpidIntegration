package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;

public interface SpidService {

    void getAuthRequest(IdpRequestDto idpRequestDto);

}
