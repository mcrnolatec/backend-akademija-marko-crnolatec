package hr.crnolatec.akademija.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthLoginResponseDTO(
        Long id,
        String username,
        String accessToken,
        String refreshToken
) {
}
