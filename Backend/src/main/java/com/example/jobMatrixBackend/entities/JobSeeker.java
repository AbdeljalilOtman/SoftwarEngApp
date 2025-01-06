package com.example.jobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;



import jakarta.persistence.*;

@Entity
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String fieldOfInterest;
    private String institutionName;
    private String programName;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certi> certifications = new ArrayList<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proj> projects = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "job_seeker_skills",
        joinColumns = @JoinColumn(name = "job_seeker_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    private String resumeUrl;
    private String portfolioUrl;

    @ManyToMany
    @JoinTable(
        name = "job_seeker_jobs_of_interest",
        joinColumns = @JoinColumn(name = "job_seeker_id"),
        inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Jobbb> jobsOfInterest = new ArrayList<>();

    // Getters and Setters
    public JobSeeker() {
    }

    // public JobSeeker(String name, String email, String password, String fieldOfInterest, String institutionName,
    //         String programName, List<Experience> experiences, List<Certi> certifications,
    //         List<Proj> projects, List<Skill> skills, String resumeUrl, String portfolioUrl,
    //         List<Jobbb> jobsOfInterest) {
    //     this.name = name;
    //     this.email = email;
    //     this.password = password;
    //     this.fieldOfInterest = fieldOfInterest;
    //     this.institutionName = institutionName;
    //     this.programName = programName;
    //     this.experiences = experiences;
    //     this.certifications = certifications;
    //     this.projects = projects;
    //     this.skills = skills;
    //     this.resumeUrl = resumeUrl;
    //     this.portfolioUrl = portfolioUrl;
    //     this.jobsOfInterest = jobsOfInterest;
    // }

    
}

