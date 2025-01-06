package com.example.JobMatrixBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JobMatrixBackend.entities.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByJobSeekerId(Long jobSeekerId);
    
}
