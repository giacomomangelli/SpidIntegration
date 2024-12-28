package it.activadigital.SpidIntegration.model.spid;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.nimbusds.jose.jwk.KeyUse;
import it.spid.cie.oidc.model.FederationEntity;
import it.spid.cie.oidc.util.GetterUtil;
import it.spid.cie.oidc.util.Validator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "federation_entity_configuration")
@Getter
@Setter
public class FederationEntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime created;

	@Column(nullable = false)
	private LocalDateTime modified;

	@Column(nullable = false)
	private String sub;

	@Column(name = "default_exp", nullable = false)
	private int defaultExpireMinutes;

	@Column(name = "default_signature_alg", nullable = false, length = 16)
	private String defaultSignatureAlg = "RS256";

	@Column(name = "authority_hints", nullable = false, length = 2000)
	private String authorityHints;

	@Column(nullable = false, length = 2000)
	private String jwksFed;

	@Column(nullable = false, length = 2000)
	private String jwksCore;
	@Column(name = "trust_marks", nullable = false, length = 2000)
	private String trustMarks;

	@Column(name = "trust_mark_issuers", nullable = false, length = 2000)
	private String trustMarkIssuers;

	@Column(nullable = false, length = 5000)
	private String metadata;

	@Column(name = "is_active", nullable = false)
	private boolean active = false;

	@Column(nullable = false, length = 5000)
	private String constraints;

	@Column(name = "entity_type", nullable = false, length = 50)
	private String entityType;

	public static FederationEntityModel of(FederationEntity source) {
		FederationEntityModel target = new FederationEntityModel();

		target.setId(source.getStorageId());
		target.setCreated(source.getCreateDate());
		target.setModified(source.getModifiedDate());
		target.setSub(source.getSubject());
		target.setDefaultExpireMinutes(source.getDefaultExpireMinutes());
		target.setDefaultSignatureAlg(source.getDefaultSignatureAlg());
		target.setEntityType(source.getEntityType());
		target.setActive(source.isActive());
		target.setAuthorityHints(source.getAuthorityHints());
		target.setConstraints(source.getConstraints());
		target.setJwksFed(source.getJwksFed());
		target.setJwksCore(source.getJwksCore());
		target.setTrustMarks(source.getTrustMarks());
		target.setTrustMarkIssuers(source.gettrustMarkIssuers());
		target.setMetadata(source.getMetadata());

		return target;
	}

	public FederationEntityModel() {
		created = LocalDateTime.now();
		modified = created;
	}

    public void setId(Long id) {
		this.id = id;
	}

	public FederationEntity toFederationEntity() {
		FederationEntity target = new FederationEntity();

		target.setStorageId(getStorageId());
		target.setCreateDate(getCreated());
		target.setModifiedDate(getModified());
		target.setSubject(getSub());
		target.setDefaultExpireMinutes(getDefaultExpireMinutes());
		target.setDefaultSignatureAlg(getDefaultSignatureAlg());
		target.setEntityType(getEntityType());
		target.setActive(isActive());
		target.setAuthorityHints(getAuthorityHints());
		target.setConstraints(getConstraints());
		target.setJwksFed(getJwksFed());
		target.setJwksCore(getJwksCore());
		target.setTrustMarks(getTrustMarks());
		target.settrustMarkIssuers(getTrustMarkIssuers());
		target.setMetadata(getMetadata());

		return target;
	}

	protected void setId(String storageId) {
		if (!Validator.isNullOrEmpty(storageId)) {
			setId(GetterUtil.getLong(storageId));
		}
	}

	private String getStorageId() {
		if (id > 0) {
			return String.valueOf(id);
		}

		return null;
	}


}
