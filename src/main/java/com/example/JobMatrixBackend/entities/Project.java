package com.example.JobMatrixBackend.entities;


import jakarta.persistence.*;
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    private String title;
    private String description;
    private String technologiesUsed;
    private String projectUrl;

    // Getters and Setters
}

