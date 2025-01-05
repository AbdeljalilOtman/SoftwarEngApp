package com.example.JobMatrixBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.jobmatrix.entities")
public class JobMatrixBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobMatrixBackendApplication.class, args);
	}

}
