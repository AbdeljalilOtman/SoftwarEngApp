package com.example.JobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Predefined or Custom

    @ManyToMany(mappedBy = "skills")
    private List<JobSeeker> jobSeekers = new ArrayList<>();

    @ManyToMany(mappedBy = "skillsRequired")
    private List<Job> jobs = new ArrayList<>();

    // Getters and Setters
}

