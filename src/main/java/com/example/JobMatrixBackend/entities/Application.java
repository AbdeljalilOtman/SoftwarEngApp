package com.example.JobMatrixBackend.entities;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private LocalDate dateApplied;
    private String status; // Pending, Accepted, Rejected

    // Getters and Setters
}

