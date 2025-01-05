package com.example.JobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String representativeName;
    private String email;
    private String password;

    private String companyLogoUrl;
    private String companyDescription;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobsPosted = new ArrayList<>();

    // Getters and Setters
}
