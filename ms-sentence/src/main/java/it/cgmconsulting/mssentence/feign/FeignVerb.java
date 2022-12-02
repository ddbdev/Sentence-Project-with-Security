package it.cgmconsulting.mssentence.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import it.cgmconsulting.mssentence.fallback.VerbFallback;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "verb" , url = "#{eurekaInstanceReceiver.getEurekaInfoByServiceName().#this.get('${eureka.service-name.verb}')}", fallback = VerbFallback.class)
public interface FeignVerb {
    @GetMapping
    String getVerb();
}
