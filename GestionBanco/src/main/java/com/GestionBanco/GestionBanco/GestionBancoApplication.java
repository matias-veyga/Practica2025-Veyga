package com.GestionBanco.GestionBanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller"+"entity")
public class GestionBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBancoApplication.class, args);
	}

}
