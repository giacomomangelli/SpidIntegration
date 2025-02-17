package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.controller.mapper.CallbackMapper;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class CallbackController {

    @Autowired
    private AssertionService assertionService;
    @Autowired
    private CacheService cacheService;

    @CrossOrigin(origins = "https://loginspid.aruba.it")
    @PostMapping("/callbackLogin")
    public void callbackAssertion(@RequestBody String samlResponse) {
        log.info("SAML Response: {}", samlResponse);
        AssertionRequestDto assertionRequestDto = CallbackMapper.mapToEntryDto(samlResponse);
        AssertionSpidResponse assertion = assertionService.checkSpidAssertion(assertionRequestDto);
        cacheService.setSpidCachedData(assertion.getResponseId(), assertion);
        redirectToFrontend(assertion.getResponseId());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/redirect/{dataId}")
    public RedirectView redirectToFrontend(@PathVariable String dataId) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(Constant.FRONTEND_URL.getDescription() + dataId);
        return redirectView;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-data/{dataId}")
    public ResponseEntity<AssertionSpidResponse> retrieveData(@PathVariable String dataId) {
        return ResponseEntity.ok(cacheService.getSpidCachedData(dataId));
    }

}
