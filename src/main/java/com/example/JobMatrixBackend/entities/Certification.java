package com.example.JobMatrixBackend.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    private String title;
    private String issuingOrganization;
    private LocalDate dateOfIssuance;
    private LocalDate expirationDate; // Nullable
    private String certificateUrl;

    // Getters and Setters
}

