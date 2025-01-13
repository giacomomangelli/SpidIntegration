package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.model.spid.AuthRequest;
import org.springframework.web.servlet.ModelAndView;

public interface SpidService {

    AuthRequestDto getAuthRequest(IdpRequestDto idpRequestDto);
    AuthRequest saveAuthRequest(AuthRequest authRequest);
    ModelAndView redirectToSSO(AuthRequestDto responseDto);
}
