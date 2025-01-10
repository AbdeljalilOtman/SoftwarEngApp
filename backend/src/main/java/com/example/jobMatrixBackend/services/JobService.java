package com.example.jobMatrixBackend.services;



import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.entities.Skill;
import com.example.jobMatrixBackend.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

@Service
public class JobService {
	
	
    private JobRepository jobRepository;
	
	private SkillRepository skillRepository;
    
	private RecruiterRepository recruiterRepository;

    @Autowired
    public JobService(JobRepository jobRepository, SkillRepository skillRepository, RecruiterRepository recruiterRepository) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.recruiterRepository = recruiterRepository;
    }



    public Jobbb createJob(Long recruiterId, Jobbb job) {
    Recruiter recruiter = recruiterRepository.findById(recruiterId).orElse(null);
    if (recruiter == null) {
        throw new RuntimeException("Recruiter not found with id " + recruiterId);
    }
    job.setRecruiter(recruiter);
    return jobRepository.save(job);
}
    
    public Jobbb updateJob(Long id, Jobbb job) throws Exception {
        if (!jobRepository.existsById(id)) {
            throw new Exception("Job not found with id " + id);
        }
        job.setId(id);  // Ensure the ID is set to the existing job
        return jobRepository.save(job);
    }
    
    public List<Jobbb> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Jobbb> getJobsByRecruiterId(Long recruiterId) {
        return jobRepository.findByRecruiterId(recruiterId);
    }

    public Optional<Jobbb> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    
    public Jobbb addSkillToJob(Long jobId, Long skillId) throws Exception {
        // Retrieve the job and skill from the database
        Jobbb job = jobRepository.findById(jobId)
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
    public List<Jobbb> findJobsByRecruiterId(Long recruiterId) {
        return jobRepository.findByRecruiterId(recruiterId);
    }


}
