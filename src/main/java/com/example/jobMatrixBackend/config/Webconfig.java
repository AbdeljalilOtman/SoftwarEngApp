package com.example.jobMatrixBackend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-cors");

        // Configure CORS for /api/** endpoints
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8081") // Replace '' with specific origins in production
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");

        // Configure CORS for /auth/** endpoints
        registry.addMapping("/auth/**")
                .allowedOrigins("") // Replace '' with specific origins in production
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}