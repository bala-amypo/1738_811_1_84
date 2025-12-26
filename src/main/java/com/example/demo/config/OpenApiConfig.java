package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth"; // Name of the security scheme
        
        return new OpenAPI()
                .info(new Info().title("Vehicle Service API").version("1.0"))
                .servers(List.of(
                        new Server().url("https://9284.pro604cr.amypo.ai/")
                ))
                // 1. Add the "Authorize" button logic
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 2. Define what the button should do (Expect a Bearer Token)
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}