package it.activadigital.SpidIntegration.model.spid;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth_request")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String esito;
    @Column(name = "issue_istant")
    private String issueIstant;
    private String uuid;
    @Column(name = "b64_request_comp")
    private String b64RequestComp;
    @Column(name = "sso_request")
    private String ssoRequest;

}
