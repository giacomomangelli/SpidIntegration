package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import it.activadigital.SpidIntegration.service.CieService;
import it.activadigital.SpidIntegration.service.MetadataService;
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
    @Autowired
    private MetadataService metadataService;
    @Autowired
    private CacheService cacheService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/metadata")
    public ResponseEntity<MetadataResponseDto> getMetadataCie(@RequestParam String clientId) {
        MetadataResponseDto dto = metadataService.getCieMetadata(clientId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/auth-request")
    public ResponseEntity<AuthRequestDto> getAuthRequest(@RequestParam String clientId) {
        AuthRequestDto responseDto = cieService.getAuthRequest(clientId);
        cieService.saveAuthRequest(AuthRequestMapper.dtoToModel(responseDto));
        cacheService.setCieCachedData(responseDto.uuid(), new AssertionCieResponse());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/getAuthData")
    public ResponseEntity<AssertionCieResponse> getAuthData(@RequestParam String uuid) {
        AssertionCieResponse assertion = cacheService.getCieCachedData(uuid);
        if (assertion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assertion);
    }

    @PostMapping("/assertionconsumer")
    public ResponseEntity<AssertionCieResponse> postAssertionConsumer(@RequestParam String samlResponse) {
        AssertionCieResponse assertion = assertionService.checkCieAssertion(samlResponse);
        cacheService.setCieCachedData(assertion.getResponseId(), assertion);
        return ResponseEntity.ok().build();
    }

}
