package com.example.qrmanagementservice.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {
    private static final String[] OPEN_ENDPOINTS = new String[]{
            "/actuator/**",
            "/v3/api-docs/**"
    };

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, JwtAuthFilter jwtAuthFilter) {
        return http
                .addFilterBefore(jwtAuthFilter, SecurityWebFiltersOrder.FORM_LOGIN)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(authRequests ->
                        authRequests
                                .pathMatchers(OPEN_ENDPOINTS).permitAll()
                                .anyExchange().authenticated()
                )
                .build();
    }
}
