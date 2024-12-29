package it.activadigital.SpidIntegration.wrapper;

import it.activadigital.SpidIntegration.conf.OidcConfig;
import it.activadigital.SpidIntegration.service.impl.RelyingPartyServiceImpl;
import it.spid.cie.oidc.callback.RelyingPartyLogoutCallback;
import it.spid.cie.oidc.config.RelyingPartyOptions;
import it.spid.cie.oidc.exception.OIDCException;
import it.spid.cie.oidc.handler.RelyingPartyHandler;
import it.spid.cie.oidc.schemas.OIDCProfile;
import it.spid.cie.oidc.schemas.ProviderButtonInfo;
import it.spid.cie.oidc.schemas.WellKnownData;
import it.spid.cie.oidc.util.Validator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Component
@Slf4j
public class RelyingPartyWrapper {

    @Autowired
    private OidcConfig oidcConfig;
    @Autowired
    private RelyingPartyServiceImpl service;
    private RelyingPartyHandler relyingPartyHandler;

    public String getAuthorizeURL(
            String spidProvider, String trustAnchor, String redirectUri, String scope,
            String profile, String prompt)
            throws OIDCException {
        return relyingPartyHandler.getAuthorizeURL(spidProvider, trustAnchor, redirectUri, scope, profile, prompt);
    }

    public List<ProviderButtonInfo> getProviderButtonInfos(OIDCProfile profile)
            throws OIDCException {
        return relyingPartyHandler.getProviderButtonInfos(profile);
    }

    public JSONObject getUserInfo(String state, String code)
            throws OIDCException {
        return relyingPartyHandler.getUserInfo(state, code);
    }

    public String getUserKey(JSONObject userInfo) {
        String userKey = userInfo.optString("sub");
        if (Validator.isNullOrEmpty(userKey)) {
            userKey = userInfo.optString("sub", "");
        }
        return userKey;
    }

    public WellKnownData getWellKnownData(String requestURL, boolean jsonMode)
            throws OIDCException {
        return relyingPartyHandler.getWellKnownData(requestURL, jsonMode);
    }

    public WellKnownData getFederationEntityData()
            throws OIDCException {
        return relyingPartyHandler.getWellKnownData(true);
    }

    public String performLogout(String userKey, RelyingPartyLogoutCallback callback)
            throws OIDCException {
        return relyingPartyHandler.performLogout(userKey, callback);
    }

    public void reloadHandler() throws OIDCException {
        log.info("reload handler");
        postConstruct();
    }

    @PostConstruct
    private void postConstruct() throws OIDCException {
        String jwkFed = readFile(oidcConfig.getRelyingParty().getJwkFedFilePath());
        String jwkCore = readFile(oidcConfig.getRelyingParty().getJwkCoreFilePath());
        String trustMarks = readFile(oidcConfig.getRelyingParty().getTrustMarksFilePath());

        log.info("final jwkFed: {}", jwkFed);
        log.info("final trust_marks: {}", trustMarks);

        RelyingPartyOptions options = new RelyingPartyOptions()
                .setDefaultTrustAnchor(oidcConfig.getDefaultTrustAnchor())
                .setCIEProviders(oidcConfig.getIdentityProviders(OIDCProfile.CIE))
                .setSPIDProviders(oidcConfig.getIdentityProviders(OIDCProfile.SPID))
                .setTrustAnchors(oidcConfig.getTrustAnchors())
                .setApplicationName(oidcConfig.getRelyingParty().getApplicationName())
                .setClientId(oidcConfig.getRelyingParty().getClientId())
                .setRedirectUris(oidcConfig.getRelyingParty().getRedirectUris())
                .setContacts(oidcConfig.getRelyingParty().getContacts())
                .setIdTokenSignedResponseAlg(oidcConfig.getRelyingParty().getIdTokenSignedResponseAlg())
                .setUserinfoSignedResponseAlg(oidcConfig.getRelyingParty().getUserinfoSignedResponseAlg())
                .setUserinfoEncryptedResponseAlg(oidcConfig.getRelyingParty().getUserinfoEncryptedResponseAlg())
                .setUserinfoEncryptedResponseEnc(oidcConfig.getRelyingParty().getUserinfoEncryptedResponseEnc())
                .setTokenEndpointAuthMethod(oidcConfig.getRelyingParty().getTokenEndpointAuthMethod())
                .setFederationResolveEndpoint(oidcConfig.getRelyingParty().getFederationResolveEndpoint())
                .setOrganizationName(oidcConfig.getRelyingParty().getOrganizationName())
                .setHomepageUri(oidcConfig.getRelyingParty().getHomepageUri())
                .setLogoUri(oidcConfig.getRelyingParty().getLogoUri())
                .setPolicyUri(oidcConfig.getRelyingParty().getPolicyUri())
                .setFederationContacts(oidcConfig.getRelyingParty().getFederationContacts())
                .setJWKFed(jwkFed)
                .setJWKCore(jwkCore)
                .setTrustMarks(trustMarks);

        relyingPartyHandler = new RelyingPartyHandler(options, service);
    }

    private String readFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.canRead()) {
                return Files.readString(file.toPath());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }


}
