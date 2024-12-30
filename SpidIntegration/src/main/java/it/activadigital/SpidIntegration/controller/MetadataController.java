package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.response.MetadataResponseDto;
import it.activadigital.SpidIntegration.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping
    public ResponseEntity<MetadataResponseDto> getMetadata(@RequestParam String clientId) {
        MetadataResponseDto dto = metadataService.getMetadata(clientId);
        return ResponseEntity.ok(dto);
    }
}