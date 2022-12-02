package it.cgmconsulting.mssentence.controller;

import it.cgmconsulting.mssentence.feign.FeignObject;
import it.cgmconsulting.mssentence.feign.FeignSubject;
import it.cgmconsulting.mssentence.feign.FeignVerb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class RequestController {

    private final FeignVerb feignVerb;
    private final FeignSubject feignSubject;

    private final FeignObject feignObject;


    @Autowired
    public RequestController(@Qualifier("it.cgmconsulting.mssentence.feign.FeignVerb") FeignVerb feignVerb, @Qualifier("it.cgmconsulting.mssentence.feign.FeignSubject") FeignSubject feignSubject, @Qualifier("it.cgmconsulting.mssentence.feign.FeignObject") FeignObject feignObject) {
        this.feignVerb = feignVerb;
        this.feignSubject = feignSubject;
        this.feignObject = feignObject;
    }

    @GetMapping("/")
    public ResponseEntity<?> getVerb() {
        return new ResponseEntity<>(feignSubject.getSubject() + " " + feignVerb.getVerb() + " " + feignObject.getObject() + ".", HttpStatus.OK);
    }
}

