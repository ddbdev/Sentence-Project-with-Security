package it.cgmconsulting.msgateway.security;

import it.cgmconsulting.msgateway.filter.AuthenticationFilter;
import it.cgmconsulting.msgateway.utils.Common;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher.MatchResult;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Set;

@Component
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationFilter jwtAuthenticationFilter() {
        return new AuthenticationFilter();
    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {

        return http
                .csrf()
                .disable()
                .authorizeExchange(exchange -> exchange
                        .matchers(this::blockUnsecured).permitAll().and()
                                .addFilterAt(jwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                        .authorizeExchange().anyExchange().permitAll()
                )
                .build();
    }

    Mono<MatchResult> blockUnsecured(final ServerWebExchange exchange ) {
        URI uri = exchange.getRequest().getURI();
        boolean invalid = "http".equalsIgnoreCase( uri.getScheme() ) &&
                Common.UNSECURED.stream().noneMatch(uri.getPath()::equalsIgnoreCase);
        return invalid ? MatchResult.notMatch() : MatchResult.match();
    }

}
