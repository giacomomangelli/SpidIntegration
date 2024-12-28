package it.activadigital.SpidIntegration.model.spid;

import it.spid.cie.oidc.model.CachedEntityInfo;
import it.spid.cie.oidc.util.GetterUtil;
import it.spid.cie.oidc.util.Validator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "fetched_entity_statement",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"iss", "sub"})
        }
)
@Getter
@Setter
public class EntityInfoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    @Column(nullable = false)
    private String iss;

    @Column(nullable = false)
    private String sub;

    @Column(nullable = false)
    private LocalDateTime exp;

    @Column(nullable = false)
    private LocalDateTime iat;

    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private String jwt;

    public static EntityInfoModel of(CachedEntityInfo source) {
        EntityInfoModel target = new EntityInfoModel();

        target.setId(source.getStorageId());
        target.setCreated(source.getCreateDate());
        target.setModified(source.getModifiedDate());
        target.setExp(source.getExpiresOn());
        target.setIat(source.getIssuedAt());
        target.setIss(source.getIssuer());
        target.setSub(source.getSubject());
        target.setJwt(source.getJwt());
        target.setStatement(source.getStatement());

        return target;
    }

    public boolean isExpired() {
        return exp.isBefore(LocalDateTime.now());
    }

    public void setId(long id) {
        this.id = id;
    }

    public CachedEntityInfo toCachedEntityInfo() {
        CachedEntityInfo target = new CachedEntityInfo();

        target.setStorageId(getStorageId());
        target.setCreateDate(getCreated());
        target.setModifiedDate(getModified());
        target.setIssuer(getIss());
        target.setSubject(getSub());
        target.setExpiresOn(getExp());
        target.setIssuedAt(getIat());
        target.setJwt(getJwt());
        target.setStatement(getStatement());

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
