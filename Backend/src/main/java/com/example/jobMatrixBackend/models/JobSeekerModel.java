package com.example.jobMatrixBackend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.repositories.JobSeekerRepository;


@Service
public class JobSeekerModel {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String fieldOfInterest;
    private String institutionName;
    private String programName;
    // private List<ExperienceModel> experiences = new ArrayList<>();
    // private List<CertificationModel> certifications = new ArrayList<>();
    // private List<ProjectModel> projects = new ArrayList<>();
    // private List<SkillModel> skills = new ArrayList<>();
    // private String resumeUrl;
    // private String portfolioUrl;
    // private List<JobModel> jobsOfInterest = new ArrayList<>();

    // Getters and Setters

    

    // Additional methods for use cases:
    // public void addExperience(ExperienceModel experience) {
    //     this.experiences.add(experience);
    // }

    // public void addCertification(CertificationModel certification) {
    //     this.certifications.add(certification);
    // }

    // public void addProject(ProjectModel project) {
    //     this.projects.add(project);
    // }

    // public void addSkill(SkillModel skill) {
    //     this.skills.add(skill);
    // }

    // public void addJobOfInterest(JobModel job) {
    //     this.jobsOfInterest.add(job);
    // }



    @Autowired
    private final JobSeekerRepository repo ; 

    public JobSeekerModel(JobSeekerRepository repo) {
        this.repo = repo;
    }
    // create new one or update existing one
    public JobSeeker createJobSeeker(JobSeeker JobSeeker) {
        return repo.save(JobSeeker);
    }
    public List<JobSeeker> getJobSeekers() {
        return repo.findAll();
    }
    public Optional<JobSeeker> getJobSeekersById(Long Id) {
        return  repo.findById(Id);
    }
}

