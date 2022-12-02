package it.cgmconsulting.msgateway.config;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class RouteLocator {
    @Bean
    public org.springframework.cloud.gateway.route.RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/user/**")
                        .uri("lb://user"))
                .route("sentence", r -> r.path("/sentence/**")
                        .uri("lb://sentence"))
                .build();
    }
}
