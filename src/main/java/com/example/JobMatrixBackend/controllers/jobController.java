package com.example.JobMatrixBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.JobMatrixBackend.entities.Job;
import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.services.JobService;
import com.example.JobMatrixBackend.services.JobMatchingService;

@RestController
@RequestMapping("/api/jobs")
public class jobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobMatchingService jobMatchingService;

    // Get all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Get a job by ID
    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Get matched job seekers for a job
    @GetMapping("/{jobId}/matched-jobseekers")
    public List<JobSeeker> getMatchedJobSeekers(@PathVariable Long jobId) {
        return jobMatchingService.matchJobSeekersForJob(jobId);
    }

    // Update a job
    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable Long jobId, @RequestBody Job jobUpdates) throws Exception {
        Job existingJob = jobService.getJobById(jobId).orElse(null);
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
        if (jobUpdates.getExperienceRequired() != null) {
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
        
        Job updatedJob = jobService.updateJob(jobId, existingJob);
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
    public ResponseEntity<Job> addSkillToJob(@PathVariable Long jobId, @PathVariable Long skillId) throws Exception {
        Job updatedJob = jobService.addSkillToJob(jobId, skillId);
        return ResponseEntity.ok(updatedJob);
    }
    
    
}
