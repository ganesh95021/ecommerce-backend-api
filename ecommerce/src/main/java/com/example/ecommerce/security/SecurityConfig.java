package com.example.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SECURITY
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // 🔓 PUBLIC APIs
                .requestMatchers(
                        "/api/users/register",
                        "/api/users/login",

                        // 🔥 SWAGGER
                        "/swagger-ui/**",
                        "/v3/api-docs/**"

                ).permitAll()

                // 🔥 USER APIs
                .requestMatchers("/api/users/all").permitAll()

                // 🔥 PRODUCT APIs
                .requestMatchers("/api/products/all").permitAll()

                .requestMatchers("/api/products/add").hasRole("ADMIN")
                .requestMatchers("/api/products/update/**").hasRole("ADMIN")
                .requestMatchers("/api/products/delete/**").hasRole("ADMIN")
                // 🔥 ALL OTHER APIs
                .anyRequest().authenticated()
            )

            // JWT FILTER
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}