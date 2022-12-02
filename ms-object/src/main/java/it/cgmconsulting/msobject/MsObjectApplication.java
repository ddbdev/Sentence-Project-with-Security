package it.cgmconsulting.msobject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsObjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsObjectApplication.class, args);
    }

}
