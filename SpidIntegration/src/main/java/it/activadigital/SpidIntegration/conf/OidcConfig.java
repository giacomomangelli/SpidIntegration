package it.activadigital.SpidIntegration.conf;

import it.spid.cie.oidc.schemas.OIDCProfile;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "oidc")
public class OidcConfig extends BaseConfig {

    public Map<String, String> getIdentityProviders(OIDCProfile profile) {
        Map<String, String> result = new HashMap<>();

        if (OIDCProfile.CIE.equals(profile)) {
            for (ProviderInfo provider : cieProviders) {
                result.put(provider.getSubject(), provider.getTrustAnchor());
            }
        }
        else if (OIDCProfile.SPID.equals(profile)) {
            for (ProviderInfo provider : spidProviders) {
                result.put(provider.getSubject(), provider.getTrustAnchor());
            }
        }

        return result;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("defaultTrustAnchor", defaultTrustAnchor);
        json.put("trustAnchors", trustAnchors);
        json.put("relyingParty", relyingParty.toJSON());
        json.put("spidProviders", new JSONArray(spidProviders));
        json.put("cieProviders", new JSONArray(cieProviders));
        json.put("hosts", hosts.toJSON());

        return json;
    }

    private String defaultTrustAnchor;
    private List<String> trustAnchors = new ArrayList<>();
    private RelyingParty relyingParty = new RelyingParty();
    private Hosts hosts = new Hosts();
    private List<ProviderInfo> spidProviders = new ArrayList<>();
    private List<ProviderInfo> cieProviders = new ArrayList<>();

    @Setter
    @Getter
    public static class Hosts extends BaseConfig {

        public JSONObject toJSON() {
            return new JSONObject()
                    .put("trust-anchor", trustAnchor)
                    .put("cie-provider", cieProvider)
                    .put("relying-party", relyingParty);
        }

        private String trustAnchor = "127.0.0.1";
        private String cieProvider = "127.0.0.1";
        private String relyingParty = "127.0.0.1";

    }

    @Getter
    @Setter
    public static class ProviderInfo extends BaseConfig {

        public JSONObject toJSON() {
            return new JSONObject()
                    .put("subject", subject)
                    .put("trust-anchor", trustAnchor);
        }

        private String subject;
        private String trustAnchor;

    }

    @Setter
    @Getter
    public static class RelyingParty {

        public Set<String> getContacts() {
            return Collections.unmodifiableSet(contacts);
        }

        public Set<String> getScope() {
            return Collections.unmodifiableSet(scope);
        }

        public Set<String> getFederationContacts() {
            return Collections.unmodifiableSet(federationContacts);
        }

        public Set<String> getRedirectUris() {
            return Collections.unmodifiableSet(redirectUris);
        }

        public JSONObject toJSON() {
            JSONObject json = new JSONObject();

            json.put("applicationName", applicationName);
            json.put("applicationType", applicationType);
            json.put("contacts", contacts);
            json.put("scope", scope);
            json.put("clientId", clientId);
            json.put("redirectUris", redirectUris);
            //json.put("jwk", jwk);
            json.put("jwkFilePath", jwkFedFilePath);
            json.put("jwkCoreFilePath", jwkCoreFilePath);
            //json.put("trustMarks", trustMarks);
            json.put("trustMarksFilePath", trustMarksFilePath);

            return json;
        }

        private String applicationName;
        private String applicationType;
        private Set<String> contacts = new HashSet<>();
        private Set<String> scope = new HashSet<>();
        private String clientId;
        private Set<String> redirectUris = new HashSet<>();
        //private String jwk;
        private String jwkFedFilePath;
        private String jwkCoreFilePath;
        //private String trustMarks;
        private String trustMarksFilePath;

        private String idTokenSignedResponseAlg;
        private String userinfoSignedResponseAlg;
        private String userinfoEncryptedResponseAlg;
        private String userinfoEncryptedResponseEnc;
        private String tokenEndpointAuthMethod;

        private String federationResolveEndpoint;
        private String organizationName;
        private String homepageUri;
        private String policyUri;
        private String logoUri;
        private Set<String> federationContacts = new HashSet<>();

    }

}