package it.activadigital.SpidIntegration.controller.dto.request;

import lombok.Getter;

public record ZoonUserRequestDto(
        String username,
        String password,
        String codiceSpid) {
}
