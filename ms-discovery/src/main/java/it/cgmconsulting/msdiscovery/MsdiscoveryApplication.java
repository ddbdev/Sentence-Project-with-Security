package it.cgmconsulting.msdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsdiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdiscoveryApplication.class, args);
	}

}
