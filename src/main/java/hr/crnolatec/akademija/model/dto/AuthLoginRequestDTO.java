package hr.crnolatec.akademija.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthLoginRequestDTO(
        @NotBlank String username,
        @NotBlank String password
) {
}
