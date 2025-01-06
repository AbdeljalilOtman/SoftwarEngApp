package com.example.JobMatrixBackend.entities;

import java.util.ArrayList;


import java.util.List;
import com.example.JobMatrixBackend.entities.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

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
    private List<Job> jobs = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<JobSeeker> getJobSeekers() {
		return jobSeekers;
	}

	public void setJobSeekers(List<JobSeeker> jobSeekers) {
		this.jobSeekers = jobSeekers;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Skill() {
		super();
	}

    
}

