package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.model.spid.AuthRequest;

public interface SpidService {

    AuthRequestDto getAuthRequest(IdpRequestDto idpRequestDto);
    void saveAuthRequest(AuthRequest authRequest);

}
