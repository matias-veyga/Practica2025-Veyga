package com.GestionBanco.GestionBanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("Controller")
public class GestionBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBancoApplication.class, args);
	}

}
