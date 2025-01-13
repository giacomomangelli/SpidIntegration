package it.activadigital.SpidIntegration.service;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;

public interface CacheService {

    AssertionSpidResponse getSpidCachedData(String key);
    AssertionCieResponse getCieCachedData(String key);
    void setSpidCachedData(String key, AssertionSpidResponse value);
    void setCieCachedData(String key, AssertionCieResponse value);
}
