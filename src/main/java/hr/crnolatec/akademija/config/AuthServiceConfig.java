package hr.crnolatec.akademija.config;

import hr.crnolatec.akademija.service.AuthService;
import hr.crnolatec.akademija.service.dummyjson.DummyJsonAuthClient;
import hr.crnolatec.akademija.service.dummyjson.DummyJsonAuthService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "app.auth-service", havingValue = "dummyjson", matchIfMissing = true)
    public AuthService dummyJsonAuthService(DummyJsonAuthClient dummyJsonAuthClient) {
        return new DummyJsonAuthService(dummyJsonAuthClient);
    }
}
