package com.GestionBanco.GestionBanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Controller", "Entity", "Service", "Implement", "Repository", "com.GestionBanco.GestionBanco"})
@EntityScan("Entity")
@EnableJpaRepositories("Repository")
public class GestionBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBancoApplication.class, args);
	}

}
