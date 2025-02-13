package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        redirectToFrontend(assertion.getResponseId());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/redirect/{dataId}")
    public RedirectView redirectToFrontend(@PathVariable String dataId) {
        RedirectView redirectView = new RedirectView();
        AssertionSpidResponse test = new AssertionSpidResponse();
        test.setResponseId(dataId);
        cacheService.setSpidCachedData(dataId, test);
        redirectView.setUrl("http://localhost:4200/spid/done/" + dataId);
        return redirectView;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-data/{dataId}")
    public ResponseEntity<AssertionSpidResponse> retrieveData(@PathVariable String dataId) {
        return ResponseEntity.ok(cacheService.getSpidCachedData(dataId));
    }

}
