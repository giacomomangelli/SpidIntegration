package it.activadigital.SpidIntegration;

import it.activadigital.SpidIntegration.conf.OidcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpidIntegrationApplication implements CommandLineRunner {

    @Autowired
    private OidcConfig oidcConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpidIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Configuration:\n" + oidcConfig.toJSONString(2));
    }

}
