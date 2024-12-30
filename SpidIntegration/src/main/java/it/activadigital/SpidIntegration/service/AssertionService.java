package it.activadigital.SpidIntegration.service;

public interface AssertionService {

    void checkSpidAssertion(String xmlAuthResponse);
    void checkCieAssertion(String xmlAuthResponse);
}
