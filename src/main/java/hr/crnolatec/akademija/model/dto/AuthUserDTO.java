package hr.crnolatec.akademija.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthUserDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String role
) {
}
