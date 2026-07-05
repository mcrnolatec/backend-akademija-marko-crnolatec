package hr.crnolatec.akademija.controller;

import hr.crnolatec.akademija.model.dto.AuthUserDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginRequestDTO;
import hr.crnolatec.akademija.model.dto.AuthLoginResponseDTO;
import hr.crnolatec.akademija.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login and get tokens")
    @ApiResponse(responseCode = "200", description = "Login response",
            content = @Content(schema = @Schema(implementation = AuthLoginResponseDTO.class)))
    public AuthLoginResponseDTO login(@Valid @RequestBody AuthLoginRequestDTO request) {
        AuthLoginResponseDTO response = authService.login(request);
        if (response == null) {
            throw new ResponseStatusException(UNAUTHORIZED, "Invalid credentials");
        }
        return response;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user")
    @ApiResponse(responseCode = "200", description = "Current user",
            content = @Content(schema = @Schema(implementation = AuthUserDTO.class)))
    public AuthUserDTO me(@AuthenticationPrincipal AuthUserDTO user) {
        return user;
    }
}
