package com.example.jobMatrixBackend.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Certi;


public interface CertificationRepository extends JpaRepository<Certi,Long> {
    List<Certi> findByJobSeekerId(Long jobSeekerId);
}
