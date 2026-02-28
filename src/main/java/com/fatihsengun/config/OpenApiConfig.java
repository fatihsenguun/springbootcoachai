package com.fatihsengun.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CoachAI REST API",
                description = "Endpoints for the AI-powered fitness and nutrition coaching application.",
                version = "1.0",
                contact = @Contact(
                        name = "Fatih Sengun",
                        email = "fatihsenguun@gmail.com"
                )
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth") // This applies the token to all endpoints globally
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication. Please insert your token below.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}