package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.model.spid.AuthRequest;

public interface CieService {

    AuthRequestDto getAuthRequest(String clientId);

    AuthRequest saveAuthRequest(AuthRequest authRequest);

}
