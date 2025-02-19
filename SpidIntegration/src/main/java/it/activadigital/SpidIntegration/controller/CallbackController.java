package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.controller.mapper.CallbackMapper;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.service.CacheService;
import it.activadigital.SpidIntegration.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class CallbackController {

    @Autowired
    private AssertionService assertionService;
    @Autowired
    private CacheService cacheService;

    @CrossOrigin(origins =
            {"https://loginspid.aruba.it, https://identity.sieltecloud.it"})
    @PostMapping(value = "/callbackLogin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView callbackAssertion(@RequestParam MultiValueMap<String,String> responseMap) {
        log.info("SAML Response: {}", responseMap);
        AssertionRequestDto assertionRequestDto = CallbackMapper.mapToEntryDto(responseMap);
        AssertionSpidResponse assertion = assertionService.checkSpidAssertion(assertionRequestDto);
        assertion.getAttributiUtente().setFiscalNumber(RequestUtil.parseFiscalNumber(assertion.getAttributiUtente().getFiscalNumber()));
        cacheService.setSpidCachedData(assertion.getResponseId(), assertion);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(Constant.FRONTEND_URL.getDescription() + assertion.getResponseId());
        return redirectView;
    }

    @CrossOrigin(origins = {"http://localhost:4200, http://localhost:4201"})
    @GetMapping("/retrieve-data/{dataId}")
    public ResponseEntity<AssertionSpidResponse> retrieveData(@PathVariable String dataId) {
        return ResponseEntity.ok(cacheService.getSpidCachedData(dataId));
    }

}
