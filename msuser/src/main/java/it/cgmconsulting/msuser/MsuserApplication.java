package it.cgmconsulting.msuser;

import it.cgmconsulting.msuser.entity.Users;
import it.cgmconsulting.msuser.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class MsuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsuserApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, PasswordEncoder passwordEncoder){


		return args -> {

			userService.save(
					new Users(1L, "useless", passwordEncoder.encode("useless"), "ROLE_USELESS")
			);
			userService.save(
					new Users(2L, "guest", passwordEncoder.encode("guest"), "ROLE_GUEST")
			);
			userService.save(
					new Users(3L, "user", passwordEncoder.encode("user"), "ROLE_USER")
			);

		};
	}

}
