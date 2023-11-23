package com.softwareengineering.planai.config.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    private static final String API_NAME = "PlanAi API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "PlanAI backend API";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(API_NAME)
                .description(API_DESCRIPTION)
                .version(API_VERSION);
    }
}
