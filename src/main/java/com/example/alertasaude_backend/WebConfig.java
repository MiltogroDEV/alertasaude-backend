package com.ogrodev.raffles_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")          // libera para todas as rotas
                        .allowedOrigins("*")        // permite qualquer origem
                        .allowedMethods("*")        // permite todos os métodos (GET, POST, PUT, DELETE, etc)
                        .allowedHeaders("*");       // permite todos os headers
            }
        };
    }
}