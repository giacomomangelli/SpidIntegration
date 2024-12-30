package it.activadigital.SpidIntegration.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributiUtenteSpidDto {

    private String spidCode;
    private String name;
    private String familyName;
    private String fiscalNumber;
    private String email;
    private String gender;
    private String dateOfBirth;
    private String placeOfBirth;
    private String countyOfBirth;
    private String idCard;
    private String address;
    private String digitalAddress;
    private String expirationDate;
    private String mobilePhone;
    private String ivaCode;
    private String registeredOffice;

}
