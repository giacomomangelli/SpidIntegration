package it.activadigital.SpidIntegration.controller.dto.request;

public record ZoonUserRequestDto(
        String username,
        String password,
        String codiceSpid) {
}
