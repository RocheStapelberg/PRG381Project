package com.rochestapelberg.PRG381Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories()
public class Prg381ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Prg381ProjectApplication.class, args);
	}

}
