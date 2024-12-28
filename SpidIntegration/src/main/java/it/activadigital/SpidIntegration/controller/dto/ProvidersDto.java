package it.activadigital.SpidIntegration.controller.dto;

import it.spid.cie.oidc.schemas.ProviderButtonInfo;

import java.util.List;

public record ProvidersDto(
        List<ProviderButtonInfo> spidProviders,
        List<ProviderButtonInfo> cieProviders) {
}
