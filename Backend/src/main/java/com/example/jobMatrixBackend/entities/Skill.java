package com.example.jobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;




import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Predefined or Custom

    @ManyToMany(mappedBy = "skills")
    private List<JobSeeker> jobSeekers = new ArrayList<>();

    @ManyToMany(mappedBy = "skillsRequired")
    private List<Jobbb> jobs = new ArrayList<>();

    // Getters and Setters
    public Skill() {
    }

    // public Skill(String name, String type, List<JobSeeker> jobSeekers, List<Jobbb> jobs) {
    //     this.name = name;
    //     this.type = type;
    //     this.jobSeekers = jobSeekers;
    //     this.jobs = jobs;
    // }

    
}

