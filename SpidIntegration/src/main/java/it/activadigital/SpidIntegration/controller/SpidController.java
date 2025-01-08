package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.controller.dto.request.IdpRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.model.mapper.AuthRequestMapper;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.MetadataService;
import it.activadigital.SpidIntegration.service.SpidService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

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
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/assertionconsumer")
    public ResponseEntity<Void> postAssertionConsumer(@RequestParam String samlResponse) {
        assertionService.checkSpidAssertion(samlResponse);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/redirectWithRedirectView")
    public ResponseEntity<Void> redirectWithUsingRedirectView() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://spring.io/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}


