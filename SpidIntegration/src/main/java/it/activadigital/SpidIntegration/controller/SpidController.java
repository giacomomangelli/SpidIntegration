package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import it.activadigital.SpidIntegration.service.MetadataService;
import it.activadigital.SpidIntegration.service.SpidService;
import it.activadigital.SpidIntegration.util.RequestUtil;
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
    @Autowired
    private MetadataService metadataService;
    @Autowired
    private RequestUtil util;
    @Autowired
    private CacheService cacheService;

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
        cacheService.setSpidCachedData(responseDto.uuid(), new AssertionSpidResponse());
        log.debug(responseDto.toString());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/callbackLogin")
    public ResponseEntity<Void> callbackAssertion(@RequestParam String samlResponse) {
        AssertionSpidResponse assertion = assertionService.checkSpidAssertion(samlResponse);
        cacheService.setSpidCachedData(assertion.getResponseId(), assertion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAuthData")
    public ResponseEntity<AssertionSpidResponse> getAuthData(@RequestParam String uuid) {
        AssertionSpidResponse assertion = cacheService.getSpidCachedData(uuid);
        if (assertion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assertion);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken() {
        return ResponseEntity.ok(util.generateToken());
    }
}


