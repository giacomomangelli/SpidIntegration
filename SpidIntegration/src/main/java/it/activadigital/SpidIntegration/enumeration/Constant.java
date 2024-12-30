package it.activadigital.SpidIntegration.enumeration;

import lombok.Getter;

@Getter
public enum Constant {
    BASE_URL("/base_url");

    final String description;

    Constant(final String description) {
        this.description = description;
    }

}