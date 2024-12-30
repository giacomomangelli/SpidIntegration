package it.activadigital.SpidIntegration.model.mapper;

import it.activadigital.SpidIntegration.controller.dto.request.AuthRequestDto;
import it.activadigital.SpidIntegration.model.spid.AuthRequest;

public class AuthRequestMapper {

    public static AuthRequest dtoToModel(AuthRequestDto dto) {
        return AuthRequest.builder()
                .esito(dto.esito())
                .b64RequestComp(dto.b64RequestComp())
                .uuid(dto.uuid())
                .ssoRequest(dto.ssoRequest())
                .issueIstant(dto.issueIstant())
                .build();
    }

}
