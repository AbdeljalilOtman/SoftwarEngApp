package com.example.JobMatrixBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan("com.example.JobMatrixBackend.entities")
@EnableJpaRepositories("com.example.JobMatrixBackend.repositories")

public class JobMatrixBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobMatrixBackendApplication.class, args);
	}

}
