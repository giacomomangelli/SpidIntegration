package it.activadigital.SpidIntegration.model.spid;

import it.spid.cie.oidc.model.AuthnRequest;
import it.spid.cie.oidc.util.GetterUtil;
import it.spid.cie.oidc.util.Validator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "oidc_authentication")
@Getter
@Setter
public class AuthnRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String state;

    @Column(nullable = true)
    private String endpoint;

    @Column(nullable = true)
    private String data;

    @Column(nullable = false)
    private boolean successful;

    @Column(name = "provider_configuration", nullable = true)
    private String providerConfiguration;

    @Column(nullable = true)
    private String provider;

    @Column(name = "provider_id", nullable = true)
    private String providerId;

    @Column(name = "provider_jwks", nullable = true)
    private String providerJwks;

    public static AuthnRequestModel of(AuthnRequest source) {
        AuthnRequestModel target = new AuthnRequestModel();
        target.setId(source.getStorageId());
        target.setCreated(source.getCreateDate());
        target.setModified(source.getModifiedDate());
        target.setClientId(source.getClientId());
        target.setData(source.getData());
        target.setEndpoint(source.getEndpoint());
        target.setProvider(source.getProvider());
        target.setProviderConfiguration(source.getProviderConfiguration());
        target.setProviderId(source.getProviderId());
        target.setProviderJwks(source.getProviderJwks());
        target.setState(source.getState());
        target.setSuccessful(source.isSuccessful());
        return target;
    }

    public AuthnRequestModel() {
        this.created = LocalDateTime.now();
        this.modified = this.created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthnRequest toAuthnRequest() {
        AuthnRequest target = new AuthnRequest();

        target.setStorageId(getStorageId());
        target.setCreateDate(getCreated());
        target.setModifiedDate(getModified());
        target.setClientId(getClientId());
        target.setData(getData());
        target.setEndpoint(getEndpoint());
        target.setProvider(getProvider());
        target.setProviderConfiguration(getProviderConfiguration());
        target.setProviderId(getProviderId());
        target.setProviderJwks(getProviderJwks());
        target.setState(getState());
        target.setSuccessful(isSuccessful());

        return target;
    }

    protected void setId(String storageId) {
        if (!Validator.isNullOrEmpty(storageId)) {
            setId(GetterUtil.getLong(storageId));
        }
    }

    private String getStorageId() {
        if (id != null && id > 0) {
            return String.valueOf(id);
        }
        return null;
    }

}
