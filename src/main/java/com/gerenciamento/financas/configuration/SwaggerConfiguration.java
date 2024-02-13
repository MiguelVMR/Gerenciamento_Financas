package com.gerenciamento.financas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(this.info());
    }

    private Info info() {
        return new Info()
                .title("Gerenciamento Swagger Application")
                .description("API de gerenciamento de finanças")
                .version("1.0.0");
    }
}
