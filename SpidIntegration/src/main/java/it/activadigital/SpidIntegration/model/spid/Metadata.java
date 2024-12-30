package it.activadigital.SpidIntegration.model.spid;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
