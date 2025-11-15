package com.example.alertasaude_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertasaudeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertasaudeBackendApplication.class, args);
	}

}
