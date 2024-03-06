package com.essentials.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi(){
        return new OpenAPI().info(new Info().title("ApiRest books").version("1.0.0")
                .license(new License().name("License system").url("https://softwaredev-six.vercel.app/")));
    }
}
