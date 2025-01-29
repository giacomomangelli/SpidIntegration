package it.activadigital.SpidIntegration.enumeration;

import lombok.Getter;

@Getter
public enum Constant {
    SPID_BASE_URL("https://auth.soluzionipa.it/spid/"),
    CIE_BASE_URL("https://auth.soluzionipa.it/cie/"),;

    final String description;

    Constant(final String description) {
        this.description = description;
    }

}