package com.example.jobMatrixBackend.entities;
import java.time.LocalDate;


import jakarta.persistence.*;
@Entity
public class Certi {
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
    public Certi() {
    }

    // public Certi(JobSeeker jobSeeker, String title, String issuingOrganization, LocalDate dateOfIssuance,
    //         LocalDate expirationDate, String certificateUrl) {
    //     this.jobSeeker = jobSeeker;
    //     this.title = title;
    //     this.issuingOrganization = issuingOrganization;
    //     this.dateOfIssuance = dateOfIssuance;
    //     this.expirationDate = expirationDate;
    //     this.certificateUrl = certificateUrl;
    // }

    
}

