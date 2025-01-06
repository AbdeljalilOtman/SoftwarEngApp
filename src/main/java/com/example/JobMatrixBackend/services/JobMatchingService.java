package com.example.JobMatrixBackend.services;

import com.example.JobMatrixBackend.entities.Experience;
import com.example.JobMatrixBackend.entities.Job;
import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.entities.Skill;
import com.example.JobMatrixBackend.repositories.JobRepository;
import com.example.JobMatrixBackend.repositories.JobSeekerRepository;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobMatchingService {

    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public JobMatchingService(JobRepository jobRepository, JobSeekerRepository jobSeekerRepository) {
        this.jobRepository = jobRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public List<JobSeeker> matchJobSeekersForJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));

        return jobSeekerRepository.findAll().stream()
                .filter(jobSeeker -> isMatching(jobSeeker, job))
                .collect(Collectors.toList());
    }

    private boolean isMatching(JobSeeker jobSeeker, Job job) {
        // Check total experience
        if (!isExperienceMatching(jobSeeker.getExperiences(), job.getExperienceRequired())) {
            return false;
        }

        // Check skills using simple string matching or NLP
        List<String> jobSeekerSkills = jobSeeker.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
        List<String> jobSkills = job.getSkillsRequired().stream().map(Skill::getName).collect(Collectors.toList());

        // Use NLP or other techniques for more advanced matching
        return isSkillMatching(jobSeekerSkills, jobSkills);
    }

    private boolean isExperienceMatching(List<Experience> experiences, int requiredExperience) {
        int totalYearsOfExperience = experiences.stream()
                .mapToInt(exp -> Period.between(exp.getStartDate(), exp.getEndDate()).getYears())
                .sum();
        return totalYearsOfExperience >= requiredExperience;
    }

    private boolean isSkillMatching(List<String> jobSeekerSkills, List<String> jobSkills) {
        // Basic matching
        return jobSkills.stream().allMatch(jobSeekerSkills::contains);
        
        // For advanced matching, use NLP tools like spaCy, OpenNLP, or custom logic
        // Example: Use spaCy for natural language processing
        // This requires integration with an NLP library
    }
    

    public List<Job> getMatchedJobsForJobSeeker(Long jobSeekerId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow();

        List<Job> allJobs = jobRepository.findAll();
        List<Job> matchedJobs = new ArrayList<>();

        for (Job job : allJobs) {
            if (isJobMatching(job, jobSeeker)) {
                matchedJobs.add(job);
            }
        }

        return matchedJobs;
    }
    
    private boolean isJobMatching(Job job, JobSeeker jobSeeker) {
        // Check experience matching
        if (!isExperienceMatching(jobSeeker.getExperiences(), job.getExperienceRequired())) {
            return false;
        }

        // Convert Skill objects to skill names for matching
        List<String> jobSkills = job.getSkillsRequired().stream().map(Skill::getName).toList();
        List<String> jobSeekerSkills = jobSeeker.getSkills().stream().map(Skill::getName).toList();

        // Check skills matching
        return isSkillMatching(jobSeekerSkills, jobSkills);
    }

}
