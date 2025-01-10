package com.example.jobMatrixBackend.entities;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Appli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobbb job;

    private LocalDate dateApplied;
    private String status; // Pending, Accepted, Rejected

    // Getters and Setters
    public Appli() {
    }

    public Appli(JobSeeker jobSeeker, Jobbb job, LocalDate dateApplied, String status) {
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.dateApplied = dateApplied;
        this.status = status;
    }

    
   

    



}

