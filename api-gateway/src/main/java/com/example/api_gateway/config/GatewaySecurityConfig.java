package com.example.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange.anyExchange().permitAll())
                .build();
    }
//        @Bean
//        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//            return http
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeExchange(auth -> auth
//                            .pathMatchers("/auth/**").permitAll()  // endpointi za prijavu i registraciju
//                            .anyExchange().authenticated()          // ostali endpointi zahtevaju autentifikaciju
//                    )
//                    .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt) // uključi JWT validaciju
//                    .build();
//        }
}
