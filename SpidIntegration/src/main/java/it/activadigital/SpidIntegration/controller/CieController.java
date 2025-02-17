package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.controller.mapper.CallbackMapper;
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
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/callbackLogin")
    public ResponseEntity<AssertionCieResponse> postAssertionConsumer(@RequestBody String samlResponse) {
        AssertionCieResponse assertion = assertionService.checkCieAssertion(CallbackMapper.mapToEntryDto(samlResponse));
        cacheService.setCieCachedData(assertion.getResponseId(), assertion);
        return ResponseEntity.ok().build();
    }

}
