package com.example.JobMatrixBackend.services;

import com.example.JobMatrixBackend.entities.Job;
import com.example.JobMatrixBackend.entities.Skill;
import com.example.JobMatrixBackend.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

@Service
public class JobService {
	
	@Autowired
    private JobRepository jobRepository;
	@Autowired
	private SkillRepository skillRepository;



    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
    
    public Job updateJob(Long id, Job job) throws Exception {
        if (!jobRepository.existsById(id)) {
            throw new Exception("Job not found with id " + id);
        }
        job.setId(id);  // Ensure the ID is set to the existing job
        return jobRepository.save(job);
    }
    
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByRecruiterId(Long recruiterId) {
        return jobRepository.findByRecruiterId(recruiterId);
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    
    public Job addSkillToJob(Long jobId, Long skillId) throws Exception {
        // Retrieve the job and skill from the database
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + jobId));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with id: " + skillId));

        // Check if the skill already exists in the job's required skills
        if (!job.getSkillsRequired().contains(skill)) {
            job.getSkillsRequired().add(skill);  // Add the skill if not present
        } else {
            throw new Exception("Skill already added to the job");
        }

        return jobRepository.save(job);  // Save the job with the updated skills
    }


}
