package com.example.JobMatrixBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JobMatrixBackend.entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    //List<Job> findByRecruiterId(Long recruiterId);
    //List<Job> findByStatus(String status);
    //List<Job> findByTitleContainingIgnoreCase(String keyword);
}

