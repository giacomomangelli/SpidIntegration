package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.SpidService;
import it.spid.cie.oidc.exception.OIDCException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Builder
@RestController
@RequestMapping("/spid")
public class SpidController {

    @Autowired
    private SpidService spidService;

    @GetMapping("/auth-request")
    public ResponseEntity<Void> getAuthRequest(@RequestParam String clientId, @RequestParam String idp) throws OIDCException {
        IdpRequestDto idpDto = new IdpRequestDto(clientId, idp);
        spidService.saveAuthRequest(AuthRequestMapper.dtoToModel(spidService.getAuthRequest(idpDto)));
        return ResponseEntity.ok().build();
    }

}


