package hr.crnolatec.akademija.service.dummyjson;

import hr.crnolatec.akademija.model.dto.AuthLoginRequestDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginResponseDTO;
import hr.crnolatec.akademija.model.dto.AuthUserDTO;
import hr.crnolatec.akademija.service.AuthService;

public class DummyJsonAuthService implements AuthService {

    private final DummyJsonAuthClient dummyJsonAuthClient;

    public DummyJsonAuthService(DummyJsonAuthClient dummyJsonAuthClient) {
        this.dummyJsonAuthClient = dummyJsonAuthClient;
    }

    @Override
    public AuthLoginResponseDTO login(AuthLoginRequestDTO request) {
        return dummyJsonAuthClient.login(request);
    }

    @Override
    public AuthUserDTO getCurrentUser(String accessToken) {
        return dummyJsonAuthClient.getCurrentUser(accessToken);
    }
}
