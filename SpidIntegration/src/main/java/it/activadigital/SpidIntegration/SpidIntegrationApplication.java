package it.activadigital.SpidIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpidIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpidIntegrationApplication.class, args);
    }

}