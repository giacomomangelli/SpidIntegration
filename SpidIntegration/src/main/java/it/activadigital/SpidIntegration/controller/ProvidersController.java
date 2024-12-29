package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.wrapper.RelyingPartyWrapper;
import it.activadigital.SpidIntegration.controller.dto.ProvidersDto;
import it.spid.cie.oidc.exception.OIDCException;
import it.spid.cie.oidc.schemas.OIDCProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idp")
public class ProvidersController {

    private RelyingPartyWrapper relyingPartyWrapper;

    @GetMapping
    public ResponseEntity<ProvidersDto> getProviders() throws OIDCException {
        return ResponseEntity.ok(new ProvidersDto(
                relyingPartyWrapper.getProviderButtonInfos(OIDCProfile.SPID),
                relyingPartyWrapper.getProviderButtonInfos(OIDCProfile.CIE)
        ));
    }
}
