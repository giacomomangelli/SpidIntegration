package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.SpidService;
import it.spid.cie.oidc.exception.OIDCException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Builder
@RestController
@RequestMapping("/spid")
public class SpidController {

    @Autowired
    private SpidService spidService;
    @Autowired
    private AssertionService assertionService;

    @GetMapping("/auth-request")
    public ResponseEntity<Void> getAuthRequest(@RequestParam String clientId, @RequestParam String idp) throws OIDCException {
        IdpRequestDto idpDto = new IdpRequestDto(clientId, idp);
        spidService.saveAuthRequest(AuthRequestMapper.dtoToModel(spidService.getAuthRequest(idpDto)));
        //todo: ritornare valori a fe per redirect
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assertionconsumer")
    public ResponseEntity<Void> postAssertionConsumer(@RequestParam String samlResponse) {
        assertionService.checkAssertion(samlResponse);
        return ResponseEntity.ok().build();
    }

}


