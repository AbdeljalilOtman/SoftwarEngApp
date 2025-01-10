package com.example.jobMatrixBackend.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Proj;

public interface ProjectRepository extends JpaRepository<Proj,Long> {
    List<Proj> findByJobSeekerId(Long jobSeekerId);
}
