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

    public Long getId() {
        return id;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Skill> getSkillsRequired() {
        return skillsRequired;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public String getStatus() {
        return status;
    }

    public List<Appli> getApplications() {
        return applications;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkillsRequired(List<Skill> skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApplications(List<Appli> applications) {
        this.applications = applications;
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

