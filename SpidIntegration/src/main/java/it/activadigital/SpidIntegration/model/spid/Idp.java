package it.activadigital.SpidIntegration.model.spid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idp {

    private String identifier;
    private String entityId;
    private String name;
    private String imageUrl;

}
