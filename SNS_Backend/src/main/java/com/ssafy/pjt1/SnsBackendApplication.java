package com.ssafy.pjt1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsBackendApplication.class, args);
	}
}
