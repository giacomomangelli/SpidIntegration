package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class CallbackController {

    @Autowired
    private AssertionService assertionService;
    @Autowired
    private CacheService cacheService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/callbackLogin")
    public void callbackAssertion(@RequestParam String samlResponse) {
        log.info("SAML Response: {}", samlResponse);
        AssertionSpidResponse assertion = assertionService.checkSpidAssertion(samlResponse);
        cacheService.setSpidCachedData(assertion.getResponseId(), assertion);
        redirectToFrontend();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/redirect")
    public RedirectView redirectToFrontend() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:4200/spid/done");
        return redirectView;
    }

}
