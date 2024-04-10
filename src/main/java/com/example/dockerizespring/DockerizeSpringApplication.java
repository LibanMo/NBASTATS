package com.example.dockerizespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DockerizeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerizeSpringApplication.class, args);
	}

}
