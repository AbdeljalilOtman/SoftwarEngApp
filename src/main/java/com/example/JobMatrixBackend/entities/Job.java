package com.example.JobMatrixBackend.entities;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "job_skills_required",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skillsRequired = new ArrayList<>();

    private int experienceRequired; // in years
    private String location;
    private String jobType; // Full-Time, Part-Time, Internship
    private LocalDate applicationDeadline;
    private String status; // Open, Closed

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();

    // Getters and Setters
}

