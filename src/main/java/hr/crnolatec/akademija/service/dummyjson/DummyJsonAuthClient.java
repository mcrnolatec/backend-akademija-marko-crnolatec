package hr.crnolatec.akademija.service.dummyjson;

import hr.crnolatec.akademija.model.dto.AuthLoginRequestDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginResponseDTO;
import hr.crnolatec.akademija.model.dto.AuthUserDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DummyJsonAuthClient {

    private static final Logger log = Logger.getLogger(DummyJsonAuthClient.class.getName());
    private static final String BASE_URL = "https://dummyjson.com";

    private final RestClient restClient = RestClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .build();

    public AuthLoginResponseDTO login(AuthLoginRequestDTO request) {
        try {
            return restClient.post()
                    .uri("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(AuthLoginResponseDTO.class);
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to login with DummyJSON", ex);
            return null;
        }
    }

    public AuthUserDTO getCurrentUser(String accessToken) {
        try {
            return restClient.get()
                    .uri("/auth/me")
                    .headers(headers -> headers.setBearerAuth(accessToken))
                    .retrieve()
                    .body(AuthUserDTO.class);
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to fetch current user from DummyJSON", ex);
            return null;
        }
    }
}
