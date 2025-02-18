package it.activadigital.SpidIntegration.model.mapper;

import it.activadigital.SpidIntegration.controller.dto.request.ZoonUserRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.ZoonUserResponseDto;
import it.activadigital.SpidIntegration.model.ZoonUser;

public class ZoonUserMapper {

    public static ZoonUser mapToEntity(ZoonUserRequestDto requestDto) {
        return ZoonUser.builder()
                .username(requestDto.username())
                .password(requestDto.password())
                .codiceSpid(requestDto.codiceSpid())
                .build();
    }

    public static ZoonUserResponseDto mapToDto(ZoonUser zoonUser) {
        if (zoonUser == null) {
            return null;
        }
        return ZoonUserResponseDto.builder()
                .id(zoonUser.getId())
                .username(zoonUser.getUsername())
                .password(zoonUser.getPassword())
                .codiceSpid(zoonUser.getCodiceSpid())
                .build();
    }

}
