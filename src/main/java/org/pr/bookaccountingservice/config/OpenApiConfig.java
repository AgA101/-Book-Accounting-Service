package org.pr.bookaccountingservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book Accounting Service API",
                version = "1.0",
                description = "API for managing books and authors"
        )
)
public class OpenApiConfig {
}