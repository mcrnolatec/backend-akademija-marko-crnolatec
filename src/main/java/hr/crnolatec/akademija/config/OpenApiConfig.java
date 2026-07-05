package hr.crnolatec.akademija.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Backend Akademija API",
                version = "1.0",
                description = "API for products"
        )
)
public class OpenApiConfig {
}
