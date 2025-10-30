package com.user;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.user.infrastructure")
@EntityScan("com.user.domain")
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		final Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
		SpringApplication.run(UserApplication.class, args);
	}

}
