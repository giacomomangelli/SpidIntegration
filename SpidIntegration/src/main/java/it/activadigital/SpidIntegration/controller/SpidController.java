package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.MetadataService;
import it.activadigital.SpidIntegration.service.SpidService;
import it.activadigital.SpidIntegration.util.CallbackCheck;
import it.activadigital.SpidIntegration.util.RequestUtil;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private MetadataService metadataService;
    @Autowired
    private RequestUtil util;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/metadata")
    public ResponseEntity<MetadataResponseDto> getMetadataSpid(@RequestParam String clientId) {
        MetadataResponseDto dto = metadataService.getSpidMetadata(clientId);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth-request")
    public ResponseEntity<AuthRequestDto> getAuthRequest(@RequestParam String clientId, @RequestParam String idp) {
        IdpRequestDto idpDto = new IdpRequestDto(clientId, idp);
        AuthRequestDto responseDto = spidService.getAuthRequest(idpDto);
        spidService.saveAuthRequest(AuthRequestMapper.dtoToModel(responseDto));
        spidService.redirectToSSO(null);
        //wait -> trigger
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/assertionconsumer")
    public ResponseEntity<Void> postAssertionConsumer(@RequestParam String samlResponse) {
        var assertion = assertionService.checkSpidAssertion(samlResponse);
        CallbackCheck springEvent = new CallbackCheck(this, "");
        applicationEventPublisher.publishEvent(springEvent);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/redirectTest")
    public ResponseEntity<String> redirectTest() {
//        spidService.redirectToSSO(null);
        return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY).body("https://www.google.com");
//        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken() {
        return ResponseEntity.ok(util.generateToken());
    }
}


