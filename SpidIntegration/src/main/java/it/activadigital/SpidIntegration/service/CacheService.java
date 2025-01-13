package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;

public interface CacheService {

    AssertionSpidResponse getCachedData(String key);
    void setCachedData(String key, AssertionSpidResponse value);
}
