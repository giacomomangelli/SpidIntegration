package it.activadigital.SpidIntegration.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {

    public static String generateToken() {
        String secret = "";

        String jws = Jwts.builder()
                .claim("start", "08012025143213")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        log.debug("TOKEN {}", jws);
        return jws;
    }
}
