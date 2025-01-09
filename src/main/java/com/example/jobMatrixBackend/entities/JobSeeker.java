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
    public String portfolioUrl;

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

    public JobSeeker(String name, String email, String password, String fieldOfInterest, String institutionName,
            String programName , String resumeUrl, String portfolioUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fieldOfInterest = fieldOfInterest;
        this.institutionName = institutionName;
        this.programName = programName;
        this.resumeUrl = resumeUrl;
        this.portfolioUrl = portfolioUrl;
        
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFieldOfInterest() {
        return fieldOfInterest;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getProgramName() {
        return programName;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Certi> getCertifications() {
        return certifications;
    }

    public List<Proj> getProjects() {
        return projects;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public List<Jobbb> getJobsOfInterest() {
        return jobsOfInterest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFieldOfInterest(String fieldOfInterest) {
        this.fieldOfInterest = fieldOfInterest;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public void setCertifications(List<Certi> certifications) {
        this.certifications = certifications;
    }

    public void setProjects(List<Proj> projects) {
        this.projects = projects;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public void setJobsOfInterest(List<Jobbb> jobsOfInterest) {
        this.jobsOfInterest = jobsOfInterest;
    }

    

    

    
}

