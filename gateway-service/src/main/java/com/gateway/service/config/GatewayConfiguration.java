package com.gateway.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all", r -> r.path("/player/all").uri("http://localhost:8000/all"))
                .route("test", r -> r.path("/player/login").uri("http://localhost:8000/login"))
                .route("test", r -> r.path("/player/registration").uri("http://localhost:8000/registration"))
                .route("test", r -> r.path("/player/profile").uri("http://localhost:8000/profile"))
                .route("test", r -> r.path("/player/roleFootball").uri("http://localhost:8000/roleFootball"))
                .route("test", r -> r.path("/player/search").uri("http://localhost:8000/search"))
                .route("test", r -> r.path("/player/id{id}").uri("http://localhost:8000/id{id}"))
                .route("test", r -> r.path("/player/profile/video").uri("http://localhost:8000/profile/video"))
                .build();
    }



}
