package hr.crnolatec.akademija.service;

import hr.crnolatec.akademija.model.dto.AuthUserDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginRequestDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginResponseDTO;

public interface AuthService {
    AuthLoginResponseDTO login(AuthLoginRequestDTO request);
    AuthUserDTO getCurrentUser(String accessToken);
}
