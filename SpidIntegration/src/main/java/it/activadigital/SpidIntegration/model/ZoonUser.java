package it.activadigital.SpidIntegration.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zoon_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(name = "codice_spid")
    private String codiceSpid;
}
