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
    @JoinColumn(name = "recruiter_id", nullable = true)
    private Recruiter recruiter;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String description;

    @ManyToMany
    @JoinTable(
        name = "job_skills_required",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skillsRequired = new ArrayList<>();

    @Column(nullable = true)
    private Integer experienceRequired; // in years

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String jobType; // Full-Time, Part-Time, Internship

    @Column(nullable = true)
    private LocalDate applicationDeadline;

    @Column(nullable = true)
    private String status; // Open, Closed

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();

    
	public Job() {
		super();
		
	}
	
	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(List<Skill> skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public Integer getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(Integer experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}


}

