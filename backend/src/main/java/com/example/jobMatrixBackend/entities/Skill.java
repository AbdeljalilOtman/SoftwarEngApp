package com.example.jobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Predefined or Custom

    @JsonBackReference
    @ManyToMany(mappedBy = "skills")
    private List<JobSeeker> jobSeekers = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "skillsRequired")
    private List<Jobbb> jobs = new ArrayList<>();

    // Getters and Setters
    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<JobSeeker> getJobSeekers() {
        return jobSeekers;
    }

    public List<Jobbb> getJobs() {
        return jobs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJobSeekers(List<JobSeeker> jobSeekers) {
        this.jobSeekers = jobSeekers;
    }

    public void setJobs(List<Jobbb> jobs) {
        this.jobs = jobs;
    }

    // public Skill(String name, String type, List<JobSeeker> jobSeekers, List<Jobbb> jobs) {
    //     this.name = name;
    //     this.type = type;
    //     this.jobSeekers = jobSeekers;
    //     this.jobs = jobs;
    // }

    

    
}

