package it.activadigital.SpidIntegration.model.spid;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import it.spid.cie.oidc.model.TrustChain;
import it.spid.cie.oidc.util.GetterUtil;
import it.spid.cie.oidc.util.Validator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trust_chain")
@Getter
@Setter
public class TrustChainModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime created;

	@Column(nullable = false)
	private LocalDateTime modified;

	@Column(name = "is_active", nullable = false)
	private boolean active;

	@Column(nullable = false)
	private String chain;

	@Column(nullable = false)
	private LocalDateTime exp;

	@Column(nullable = false)
	private LocalDateTime iat;

	@Column(nullable = false)
	private String log;

	@Column(nullable = true)
	private String metadata;

	@Column(name = "parties_involved", nullable = false)
	private String partiesInvolved;

	@Column(name = "processing_start", nullable = false)
	private LocalDateTime processingStart;

	@Column(nullable = false)
	private String sub;

	@Column(nullable = false)
	private String status;

	@Transient
	private String trustAnchor;

	@Column(name = "trust_anchor_id", nullable = false)
	private long trustAnchorId;

	@Column(name = "trust_masks", nullable = false)
	private String trustMasks;

	@Column(name = "type_", nullable = false)
	private String type;

	public static TrustChainModel of(TrustChain source, EntityInfoModel trustAnchorModel) {

		TrustChainModel target = new TrustChainModel();

		target.setId(source.getStorageId());
		target.setCreated(source.getCreateDate());
		target.setModified(source.getModifiedDate());
		target.setSub(source.getSubject());
		target.setType(source.getType());
		target.setExp(source.getExpiresOn());
		target.setIat(source.getIssuedAt());
		target.setChain(source.getChain());
		target.setPartiesInvolved(source.getPartiesInvolved());
		target.setActive(source.isActive());
		target.setLog(source.getLog());
		target.setMetadata(source.getMetadata());
		target.setProcessingStart(source.getProcessingStart());
		target.setTrustAnchorId(trustAnchorModel.getId());
		target.setTrustAnchor(trustAnchorModel.getSub());
		target.setTrustMasks(source.getTrustMarks());
		target.setStatus(source.getStatus());

		return target;
	}

	public TrustChainModel() {
		created = LocalDateTime.now();
		modified = created;
	}

	public boolean isExpired() {
		return exp.isBefore(LocalDateTime.now());
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrustChain toTrustChain(EntityInfoModel trustAnchorModel) {
		TrustChain target = new TrustChain();

		target.setStorageId(getStorageId());
		target.setCreateDate(getCreated());
		target.setModifiedDate(getModified());
		target.setActive(isActive());
		target.setChain(getChain());
		target.setExpiresOn(getExp());
		target.setIssuedAt(getIat());
		target.setLog(getLog());
		target.setMetadata(getMetadata());
		target.setPartiesInvolved(getPartiesInvolved());
		target.setProcessingStart(getProcessingStart());
		target.setSubject(getSub());
		target.setStatus(getStatus());
		target.setTrustMarks(getTrustMasks());
		target.setType(getType());

		if (trustAnchorModel != null) {
			target.setTrustAnchor(trustAnchorModel.getSub());
		}

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
