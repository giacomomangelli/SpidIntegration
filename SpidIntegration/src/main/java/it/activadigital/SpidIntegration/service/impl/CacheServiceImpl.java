package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    @Cacheable("context")
    public AssertionSpidResponse getSpidCachedData(String key) {
        if (cacheManager.getCache("context") != null) {
            var t = cacheManager.getCache("context").get(key);
            log.info("getCachedData: {}", t);
            if (t != null) {
                return (AssertionSpidResponse) t;
            }
        }
        return null;
    }

    @Override
    public AssertionCieResponse getCieCachedData(String key) {
        if (cacheManager.getCache("context") != null) {
            var t = cacheManager.getCache("context").get(key);
            log.info("getCachedData: {}", t);
            if (t != null) {
                return (AssertionCieResponse) t;
            }
        }
        return null;
    }

    @Override
    @CachePut("context")
    public void setSpidCachedData(String key, AssertionSpidResponse value) {
        if (cacheManager.getCache("context") != null) {
            cacheManager.getCache("context").put(key, value);
            log.info("setCachedData: {}", value);
        }
    }

    @Override
    public void setCieCachedData(String key, AssertionCieResponse value) {
        if (cacheManager.getCache("context") != null) {
            cacheManager.getCache("context").put(key, value);
            log.info("setCachedData: {}", value);
        }
    }
}
