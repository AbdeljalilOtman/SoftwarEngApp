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

