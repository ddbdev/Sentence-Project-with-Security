package it.cgmconsulting.mssentence.feign;

import it.cgmconsulting.mssentence.fallback.ObjectFallback;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "object" , url = "#{eurekaInstanceReceiver.getEurekaInfoByServiceName().#this.get('${eureka.service-name.object}')}", fallback = ObjectFallback.class)
public interface FeignObject {
    @GetMapping(value = "/")
    String getObject();
}
