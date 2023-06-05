package com.boardgame.gateway.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authservice", r -> r.path("/api/auth/**")
                        .uri("http://authservice:8081"))
                .route("userservice", r -> r.path("/api/user/**")
                        .uri("http://userservice:8082"))
                .route("contractservice", r -> r.path("/api/contract/**")
                        .uri("http://contractservice:8083"))
                .route("boardgameservice", r -> r.path("/api/boardgame/**")
                        .uri("http://boardgameservice:8084"))
                .build();
    }
}
