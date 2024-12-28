package it.activadigital.SpidIntegration.model.spid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDecoded {

    private String codiceIdentificativo;
    private String nome;
    private String cognome;

    private String luogoNascita;
    private String provinciaNascita;
    private Date dataNascita;

    private String sesso;
    private String ragioneSociale;

    private String indirizzoSedeLegale;
    private String codiceFiscale;
    private String partitaIva;

    private String documentoIdentita;
    private Date dataScadenzaIdentita;

    private String indirizzoDomicilio;
    private String numeroTelefono;
    private String emailAddress;
    private String emailPec;

}
