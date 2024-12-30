package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cie")
@RestController
public class CieController {

    @Autowired
    private CieService cieService;
    @Autowired
    private AssertionService assertionService;

    @GetMapping("/auth-request")
    public ResponseEntity<AuthRequestDto> getAuthRequest(@RequestParam String clientId) {
        AuthRequestDto responseDto = cieService.getAuthRequest(clientId);
        cieService.saveAuthRequest(AuthRequestMapper.dtoToModel(responseDto));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/assertionconsumer")
    public ResponseEntity<Void> postAssertionConsumer(@RequestParam String samlResponse) {
        assertionService.checkCieAssertion(samlResponse);
        return ResponseEntity.ok().build();
    }
}
