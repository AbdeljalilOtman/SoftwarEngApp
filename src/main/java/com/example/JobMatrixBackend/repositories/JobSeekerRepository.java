package com.example.JobMatrixBackend.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.entities.User;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
	Optional<JobSeeker> findByEmail(String email);
}

