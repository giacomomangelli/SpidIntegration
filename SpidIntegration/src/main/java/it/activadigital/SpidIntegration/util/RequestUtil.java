package it.activadigital.SpidIntegration.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
public class RequestUtil {

    public static String generateToken() {
        String secret = "";
        String jws = Jwts.builder()
                .claim("start", "08012025143213")
                .claim("hash_assertion_consumer", createHashForSpid())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        log.debug("TOKEN {}", jws);
        return jws;
    }

    public static MultiValueMap<String, String> setHeaders() {
        MultiValueMap<String, String> multiHeaders = new LinkedMultiValueMap<>();
        multiHeaders.add("Authorization", "Bearer " + generateToken());
        multiHeaders.add("Accept", "application/json");
        multiHeaders.add("Accept-Charset", "utf-8");
        log.debug("HEADERS {}", multiHeaders);
        return multiHeaders;
    }

    public static String createHashForSpid() {
        return "";
    }
}
