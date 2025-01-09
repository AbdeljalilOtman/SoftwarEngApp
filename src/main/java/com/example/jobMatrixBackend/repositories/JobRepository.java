package com.example.jobMatrixBackend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Jobbb;

@EnableJpaRepositories
public interface JobRepository extends JpaRepository<Jobbb,Long> {
    List<Jobbb> findByRecruiterId(Long recruiterId);
    List<Jobbb> findByStatus(String status);
    List<Jobbb> findByTitleContainingIgnoreCase(String keyword);
}

