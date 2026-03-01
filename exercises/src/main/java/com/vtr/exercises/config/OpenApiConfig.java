package com.vtr.exercises.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trainer Hub API")
                        .version("v1")
                        .description("""
                                RESTful API for managing trainers, workouts, exercises
                                and performance tracking within the Trainer Hub platform.
                                """)
                        .termsOfService("https://trainerhub.com/terms")
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );

    }
}
