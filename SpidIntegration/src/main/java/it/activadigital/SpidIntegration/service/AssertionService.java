package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;

public interface AssertionService {

    AssertionSpidResponse checkSpidAssertion(String xmlAuthResponse);
    AssertionCieResponse checkCieAssertion(String xmlAuthResponse);
}
