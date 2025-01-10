package com.example.jobMatrixBackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Proj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    private String title;
    private String description;
    private String technologiesUsed;
    private String projectUrl;

    // Getters and Setters
    public Proj() {
    }

    // public Proj(JobSeeker jobSeeker, String title, String description, String technologiesUsed, String projectUrl) {
    //     this.jobSeeker = jobSeeker;
    //     this.title = title;
    //     this.description = description;
    //     this.technologiesUsed = technologiesUsed;
    //     this.projectUrl = projectUrl;
    // }

    
}

