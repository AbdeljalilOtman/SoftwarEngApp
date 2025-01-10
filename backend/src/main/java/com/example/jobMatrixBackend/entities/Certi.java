package com.example.jobMatrixBackend.entities;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
@Entity
public class Certi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


   // @JsonBackReference
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

    public Long getId() {
        return id;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public String getTitle() {
        return title;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public LocalDate getDateOfIssuance() {
        return dateOfIssuance;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public void setDateOfIssuance(LocalDate dateOfIssuance) {
        this.dateOfIssuance = dateOfIssuance;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
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

