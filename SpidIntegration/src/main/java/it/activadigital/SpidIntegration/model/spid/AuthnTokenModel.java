package it.activadigital.SpidIntegration.model.spid;

import it.spid.cie.oidc.model.AuthnToken;
import it.spid.cie.oidc.util.GetterUtil;
import it.spid.cie.oidc.util.Validator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "oidc_authentication_token")
@Getter
@Setter
public class AuthnTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    @Column(nullable = true)
    private String code;

    @Column(name = "access_token", nullable = true)
    private String accessToken;

    @Column(name = "id_token", nullable = true)
    private String idToken;

    @Column(nullable = true)
    private String scope;

    @Column(name = "token_type", nullable = true)
    private String tokenType;

    @Column(name = "expires_in", nullable = true)
    private int expiresIn;

    @Column(name = "authz_request_id", nullable = false)
    private long authzRequestId;

    @Column(name = "user_key", nullable = true)
    private String userKey;

    @Column(nullable = true)
    private LocalDateTime revoked;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;

    public static AuthnTokenModel of(AuthnToken source) {
        AuthnTokenModel target = new AuthnTokenModel();

        target.setId(source.getStorageId());
        target.setCreated(source.getCreateDate());
        target.setModified(source.getModifiedDate());
        target.setAccessToken(source.getAccessToken());
        target.setAuthzRequestId(source.getAuthnRequestId());
        target.setCode(source.getCode());
        target.setExpiresIn(source.getExpiresIn());
        target.setIdToken(source.getIdToken());
        target.setRefreshToken(source.getRefreshToken());
        target.setRevoked(source.getRevoked());
        target.setScope(source.getScope());
        target.setTokenType(source.getTokenType());
        target.setUserKey(source.getUserKey());

        return target;
    }

    public AuthnTokenModel() {
        this.created = LocalDateTime.now();
        this.modified = this.created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthzRequestId(long authzRequestId) {
        this.authzRequestId = authzRequestId;
    }

    public AuthnToken toAuthnToken() {
        AuthnToken target = new AuthnToken();

        target.setStorageId(getStorageId());
        target.setCreateDate(getCreated());
        target.setModifiedDate(getModified());
        target.setAccessToken(getAccessToken());
        target.setAuthnRequestId(String.valueOf(getAuthzRequestId()));
        target.setCode(getCode());
        target.setExpiresIn(getExpiresIn());
        target.setIdToken(getIdToken());
        target.setRefreshToken(getRefreshToken());
        target.setRevoked(getRevoked());
        target.setScope(getScope());
        target.setTokenType(getTokenType());
        target.setUserKey(getUserKey());

        return target;
    }

    protected void setAuthzRequestId(String authnRequestId) {
        setAuthzRequestId(GetterUtil.getLong(authnRequestId));
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
