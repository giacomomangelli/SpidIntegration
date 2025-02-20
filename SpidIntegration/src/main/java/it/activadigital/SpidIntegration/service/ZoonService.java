package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.request.ZoonUserRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.ZoonUserResponseDto;

import java.util.List;

public interface ZoonService {

    void updateZoonUserPwd(ZoonUserRequestDto zoonDto);

    void addZoonUser(ZoonUserRequestDto zoonDto);

    ZoonUserResponseDto getZoonUser(String username);

    List<ZoonUserResponseDto> listZoonUser();

    void deleteZoonUser(String username);

}
