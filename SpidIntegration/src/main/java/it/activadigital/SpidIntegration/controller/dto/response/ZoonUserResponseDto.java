package it.activadigital.SpidIntegration.controller.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoonUserResponseDto {

    private Long id;
    private String username;
    private String password;
    private String codiceSpid;

}
