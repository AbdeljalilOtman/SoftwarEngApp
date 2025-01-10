package com.example.jobMatrixBackend.controller;



import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.repositories.JobSeekerRepository;
import com.example.jobMatrixBackend.services.JobSeekerModel;
import com.example.jobMatrixBackend.util.JwtUtil;


@RestController
@RequestMapping("/api/job-seekers")
@CrossOrigin(origins = "http://localhost:8081")
public class JobSeekerController {

    private final JobSeekerModel jobSeekerModel;
    private final JobSeekerRepository jobSeekerRepository;

    @Autowired
    public JobSeekerController(JobSeekerModel jobSeekerModel, JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerModel = jobSeekerModel;
        this.jobSeekerRepository = jobSeekerRepository;
    }


    @PostMapping(value = "/register", consumes = {"application/json", "application/json;charset=UTF-8"}, produces = "application/json")
    public ResponseEntity<?> registerJobSeeker(@RequestBody JobSeeker jobSeeker) {
        // Save the job seeker to the database
        JobSeeker savedJobSeeker = jobSeekerRepository.save(jobSeeker);
        
        // Generate JWT token for the new job seeker
        String token = JwtUtil.generateToken(savedJobSeeker.getEmail()); // Or any unique identifier
        
        // Return job seeker and token in the response
        return ResponseEntity.ok(new LoginResponse(savedJobSeeker, token));
    }

   @GetMapping("/login")
public ResponseEntity<?> loginJobSeeker(@RequestParam String email, @RequestParam String password) {
    System.out.println("email: " + email + " password: " + password);
    Optional<JobSeeker> existingJobSeeker = jobSeekerModel.getJobSeekersByEmail(email);
    System.out.println("existingJobSeeker: " + existingJobSeeker);

    if (existingJobSeeker.isPresent() && existingJobSeeker.get().getPassword().equals(password)) {
        String token = JwtUtil.generateToken(email);

        return ResponseEntity.ok(new LoginResponse(existingJobSeeker.get(), token));
        //return ResponseEntity.ok(existingJobSeeker.get());
    } else {
        return ResponseEntity.status(401).build(); // Return 401 Unauthorized response
    }
}


    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getJobSeekerById(@PathVariable Long id) {
        Optional<JobSeeker> jobSeeker = jobSeekerRepository.findById(id);
        return jobSeeker.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeeker> updateJobSeeker(@PathVariable Long id, @RequestBody JobSeeker jobSeekerDetails) {
        return jobSeekerRepository.findById(id).map(jobSeeker -> {
            if (jobSeekerDetails.getName() != null) jobSeeker.setName(jobSeekerDetails.getName());
            if (jobSeekerDetails.getFieldOfInterest() != null) jobSeeker.setFieldOfInterest(jobSeekerDetails.getFieldOfInterest());
            if (jobSeekerDetails.getInstitutionName() != null) jobSeeker.setInstitutionName(jobSeekerDetails.getInstitutionName());
            if (jobSeekerDetails.getProgramName() != null) jobSeeker.setProgramName(jobSeekerDetails.getProgramName());
            if (jobSeekerDetails.getResumeUrl() != null) jobSeeker.setResumeUrl(jobSeekerDetails.getResumeUrl());
            if (jobSeekerDetails.getPortfolioUrl() != null) jobSeeker.setPortfolioUrl(jobSeekerDetails.getPortfolioUrl());
        
            JobSeeker updatedJobSeeker = jobSeekerRepository.save(jobSeeker);
            return ResponseEntity.ok(updatedJobSeeker);
        }).orElse(ResponseEntity.notFound().build());
    }
    public static class LoginResponse {
        private JobSeeker user;
        private String token;
    
        public LoginResponse(JobSeeker user, String token) {
            this.user = user;
            this.token = token;
        }
    
        public JobSeeker getUser() {
            return user;
        }
    
        public void setUser(JobSeeker user) {
            this.user = user;
        }
    
        public String getToken() {
            return token;
        }
    
        public void setToken(String token) {
            this.token = token;
        }
    }
}

// Define LoginResponse as an inner class or separate class


