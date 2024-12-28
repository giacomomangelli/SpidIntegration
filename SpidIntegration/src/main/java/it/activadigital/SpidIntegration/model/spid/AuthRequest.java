package it.activadigital.SpidIntegration.model.spid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    private String xmlAuthRequest;
    private String destinationUrl;
}
