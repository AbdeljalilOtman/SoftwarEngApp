package com.example.jobMatrixBackend.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Appli;


public interface ApplicationRepository extends JpaRepository<Appli,Long> {
    List<Appli> findByJobId(Long jobId);
    List<Appli> findByJobSeekerId(Long jobSeekerId);
}

