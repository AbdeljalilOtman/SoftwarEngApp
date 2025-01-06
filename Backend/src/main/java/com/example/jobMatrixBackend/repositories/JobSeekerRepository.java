package com.example.jobMatrixBackend.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.JobSeeker;

@EnableJpaRepositories
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Long> {
    Optional<JobSeeker> findByEmail(String email);
}

