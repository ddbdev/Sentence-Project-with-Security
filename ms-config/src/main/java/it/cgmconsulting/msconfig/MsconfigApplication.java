package it.cgmconsulting.msconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.util.ConditionalOnBootstrapEnabled;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class MsconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsconfigApplication.class, args);
	}

}
