package it.activadigital.SpidIntegration.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Instant;

@Slf4j
@Component
public class RequestUtil {

    @Value("${deda-client.secret}")
    private String secret;

    public String generateToken() {
        String jws = Jwts.builder()
                .claim("start", Instant.now().getEpochSecond())
                .claim("hash_assertion_consumer", createHashForSpid())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        log.debug("TOKEN {}", jws);
        return jws;
    }

    public MultiValueMap<String, String> setHeaders() {
        MultiValueMap<String, String> multiHeaders = new LinkedMultiValueMap<>();
        multiHeaders.add("Authorization", "Bearer " + generateToken());
        multiHeaders.add("Accept", "application/json");
        multiHeaders.add("Accept-Charset", "utf-8");
        log.debug("HEADERS {}", multiHeaders);
        return multiHeaders;
    }

    public String createHashForSpid() {
        return "";
    }

    @Cacheable(value = "json_response", key = "uuid")
    public AssertionSpidResponse extractJsonResponse(String uuid, AssertionSpidResponse body) {
        return body;
    }
}
