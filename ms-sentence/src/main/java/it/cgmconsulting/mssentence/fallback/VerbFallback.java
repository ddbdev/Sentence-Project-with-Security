package it.cgmconsulting.mssentence.fallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import it.cgmconsulting.mssentence.feign.FeignVerb;
import org.springframework.stereotype.Component;

@Component
public class VerbFallback implements FeignVerb {
    @Override
    public String getVerb() {
        return "(Service verb is not avaiable)";
    }
}
