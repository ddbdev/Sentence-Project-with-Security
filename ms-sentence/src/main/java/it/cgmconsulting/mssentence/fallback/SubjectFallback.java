package it.cgmconsulting.mssentence.fallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import it.cgmconsulting.mssentence.feign.FeignSubject;
import org.springframework.stereotype.Component;


@Component
public class SubjectFallback implements FeignSubject {
    @Override
    public String getSubject() {
        return "(Service subject is not avaiable)";
    }
}
