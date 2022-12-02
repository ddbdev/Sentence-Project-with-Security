package it.cgmconsulting.mssentence.feign;

import it.cgmconsulting.mssentence.fallback.SubjectFallback;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "subject" , url = "#{eurekaInstanceReceiver.getEurekaInfoByServiceName().#this.get('${eureka.service-name.subject}')}", fallback = SubjectFallback.class)
public interface FeignSubject {

    @GetMapping(value = "/")
    String getSubject();
}
