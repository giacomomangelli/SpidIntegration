package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.CieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cie")
@RestController
public class CieController {

    @Autowired
    private CieService cieService;

    @GetMapping("/auth-request")
    public ResponseEntity<AuthRequestDto> getAuthRequest(@RequestParam String clientId) {
        AuthRequestDto responseDto = cieService.getAuthRequest(clientId);
        cieService.saveAuthRequest(AuthRequestMapper.dtoToModel(responseDto));
        return ResponseEntity.ok(responseDto);
    }

}
