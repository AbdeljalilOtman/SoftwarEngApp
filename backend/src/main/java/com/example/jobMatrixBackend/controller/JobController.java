package com.example.jobMatrixBackend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.services.JobMatchingService;
import com.example.jobMatrixBackend.services.JobService;
import com.example.jobMatrixBackend.entities.JobSeeker;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:8081")
public class JobController {

    @Autowired
    private JobService jobService;

    

    // Get all jobs
    @GetMapping
    public List<Jobbb> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Get a job by ID
    @GetMapping("/{id}")
    public Optional<Jobbb> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Get matched job seekers for a job
    // @GetMapping("/{jobId}/matched-jobseekers")
    // public List<JobSeeker> getMatchedJobSeekers(@PathVariable Long jobId) {
    //     return jobMatchingService.matchJobSeekersForJob(jobId);
    // }

    // Update a job
    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable Long jobId, @RequestBody Jobbb jobUpdates) throws Exception {
        Jobbb existingJob = jobService.getJobById(jobId).orElse(null);
        if (existingJob == null) {
            return ResponseEntity.badRequest().body("Job with ID " + jobId + " not found");
        }

        // Only update fields that are not null
        if (jobUpdates.getTitle() != null) {
            existingJob.setTitle(jobUpdates.getTitle());
        }
        if (jobUpdates.getDescription() != null) {
            existingJob.setDescription(jobUpdates.getDescription());
        }
        if (jobUpdates.getExperienceRequired() != 0) {
            existingJob.setExperienceRequired(jobUpdates.getExperienceRequired());
        }
        if (jobUpdates.getLocation() != null) {
            existingJob.setLocation(jobUpdates.getLocation());
        }
        
        if (jobUpdates.getJobType() != null) {  // Add jobType field check
            existingJob.setJobType(jobUpdates.getJobType());
        }
        if (jobUpdates.getApplicationDeadline() != null) {  // Add applicationDeadline field check
            existingJob.setApplicationDeadline(jobUpdates.getApplicationDeadline());
        }
        if (jobUpdates.getStatus() != null) {  // Add status field check
            existingJob.setStatus(jobUpdates.getStatus());
        }
        
        Jobbb updatedJob = jobService.updateJob(jobId, existingJob);
        return ResponseEntity.ok(updatedJob);
    }

    
    
    // Delete a job
    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
        if (!jobService.getJobById(jobId).isPresent()) {
            return ResponseEntity.badRequest().body("Job with ID " + jobId + " not found");
        }

        jobService.deleteJob(jobId);
        return ResponseEntity.ok().body("Job with ID " + jobId + " has been deleted");
    }
    
    
    
    @PostMapping("/{jobId}/skills/{skillId}")
    public ResponseEntity<Jobbb> addSkillToJob(@PathVariable Long jobId, @PathVariable Long skillId) throws Exception {
        Jobbb updatedJob = jobService.addSkillToJob(jobId, skillId);
        return ResponseEntity.ok(updatedJob);
    }

    @PostMapping("/create/{recruiterId}")
    public ResponseEntity<Jobbb> createJob(@PathVariable Long recruiterId, @RequestBody Jobbb job) {
        Jobbb createdJob = jobService.createJob(recruiterId, job);
        return ResponseEntity.ok(createdJob);
    }


    
    
}
