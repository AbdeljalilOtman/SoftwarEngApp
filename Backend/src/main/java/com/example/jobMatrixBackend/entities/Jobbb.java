package com.example.jobMatrixBackend.entities;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;


import jakarta.persistence.*;

@Entity
public class Jobbb {
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
    private List<Appli> applications = new ArrayList<>();

    // Getters and Setters
    public Jobbb() {
    }

    // public Jobbb(Recruiter recruiter, String title, String description, List<Skill> skillsRequired,
    //         int experienceRequired, String location, String jobType, LocalDate applicationDeadline, String status,
    //         List<Appli> applications) {
    //     this.recruiter = recruiter;
    //     this.title = title;
    //     this.description = description;
    //     this.skillsRequired = skillsRequired;
    //     this.experienceRequired = experienceRequired;
    //     this.location = location;
    //     this.jobType = jobType;
    //     this.applicationDeadline = applicationDeadline;
    //     this.status = status;
    //     this.applications = applications;
    // }

    
}

