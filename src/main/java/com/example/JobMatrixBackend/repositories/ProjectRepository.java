package com.example.JobMatrixBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JobMatrixBackend.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    //List<Project> findByJobSeekerId(Long jobSeekerId);
}
