package com.example.jobMatrixBackend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Experience;


public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findByJobSeekerId(Long jobSeekerId);
}
