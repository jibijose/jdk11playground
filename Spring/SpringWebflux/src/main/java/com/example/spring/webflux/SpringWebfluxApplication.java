package com.example.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.spring.webflux.repository")
public class SpringWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
	}

}
