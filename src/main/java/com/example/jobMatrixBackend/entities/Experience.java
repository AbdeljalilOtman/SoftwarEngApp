package com.example.jobMatrixBackend.entities;
import java.time.LocalDate;


import jakarta.persistence.*;
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    private String position;
    private String companyName;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public String getPosition() {
        return position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    // Getters and Setters
    public Experience() {
    }

    // public Experience(JobSeeker jobSeeker, String position, String companyName, LocalDate startDate, LocalDate endDate,
    //         String description) {
    //     this.jobSeeker = jobSeeker;
    //     this.position = position;
    //     this.companyName = companyName;
    //     this.startDate = startDate;
    //     this.endDate = endDate;
    //     this.description = description;
    // }

    
    
}

