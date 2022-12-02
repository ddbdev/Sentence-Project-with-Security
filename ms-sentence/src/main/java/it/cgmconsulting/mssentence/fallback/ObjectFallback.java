package it.cgmconsulting.mssentence.fallback;


import it.cgmconsulting.mssentence.feign.FeignObject;
import org.springframework.stereotype.Component;

@Component
public class ObjectFallback implements FeignObject {
    @Override
    public String getObject() {
        return "(Service object is not avaiable)";
    }
}
