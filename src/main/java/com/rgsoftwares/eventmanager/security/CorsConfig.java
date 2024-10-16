package com.rgsoftwares.eventmanager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Mapeie o caminho da API
                .allowedOrigins("http://localhost:4200")  // Defina a origem permitida
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permita o método OPTIONS (preflight)
                .allowedHeaders("*")  // Permita todos os cabeçalhos
                .exposedHeaders("Authorization")  // Exponha cabeçalhos específicos, se necessário
                .allowCredentials(true)  // Permita envio de credenciais (cookies, autenticação)
                .maxAge(3600);  // Cache da preflight request por 1 hora
    }
}
