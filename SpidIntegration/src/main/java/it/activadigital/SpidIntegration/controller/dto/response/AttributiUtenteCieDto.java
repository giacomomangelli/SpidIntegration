package it.activadigital.SpidIntegration.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributiUtenteCieDto {

    private String name;
    private String familyName;
    private String fiscalNumber;
    private String dateOfBirth;

}
