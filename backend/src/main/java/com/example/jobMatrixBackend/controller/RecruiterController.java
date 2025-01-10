package com.example.jobMatrixBackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.repositories.RecruiterRepository;
import com.example.jobMatrixBackend.services.JobService;
import com.example.jobMatrixBackend.services.RecruiterService;
import com.example.jobMatrixBackend.util.JwtUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping("/api/recruiter")
@CrossOrigin(origins = "http://localhost:8081")
public class RecruiterController {
    

    private final RecruiterService RecruiterService;
    private final RecruiterRepository recruiterRepository;
    private final JobService jobService;

    @Autowired
    public RecruiterController(RecruiterService RecruiterService, RecruiterRepository recruiterRepository, JobService jobService) {
        this.RecruiterService = RecruiterService;
        this.recruiterRepository = recruiterRepository;
        this.jobService = jobService;
    }


    @PostMapping(value = "/register", consumes = {"application/json", "application/json;charset=UTF-8"}, produces="application/json")
    public ResponseEntity<?> registerRecruiter(@RequestBody Recruiter recruiter ) {
        // Save the recruiter to the database
        Recruiter savedRecruiter = recruiterRepository.save(recruiter);
        
        // Generate JWT token for the new recruiter
        String token = JwtUtil.generateToken(savedRecruiter.getEmail()); // Or any unique identifier
        
        // Return recruiter and token in the response
        //return ResponseEntity.ok(new LoginResponse(savedRecruiter, token));
        return ResponseEntity.ok(savedRecruiter);
    }


    @GetMapping("/login")
    public ResponseEntity<?> loginRecruiter(@RequestParam String email, @RequestParam String password) {
        Optional<Recruiter> existingRecruiter = RecruiterService.getRecruitersByEmail(email);
        
        if (existingRecruiter.isPresent() && existingRecruiter.get().getPassword().equals(password)) {
            String token = JwtUtil.generateToken(email);
            return ResponseEntity.ok(new LoginResponse(existingRecruiter.get(), token));
        } else {
            return ResponseEntity.status(401).build(); // Return 401 Unauthorized response
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        return recruiter.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable Long id, @RequestBody Recruiter recruiterDetails) {
        return recruiterRepository.findById(id).map(recruiter -> {
            if (recruiterDetails.getCompanyName() != null) recruiter.setCompanyName(recruiterDetails.getCompanyName());
            if (recruiterDetails.getRepresentativeName() != null) recruiter.setRepresentativeName(recruiterDetails.getRepresentativeName());
            if (recruiterDetails.getCompanyLogoUrl() != null) recruiter.setCompanyLogoUrl(recruiterDetails.getCompanyLogoUrl());
            if (recruiterDetails.getCompanyDescription() != null) recruiter.setCompanyDescription(recruiterDetails.getCompanyDescription());
        
            Recruiter updatedRecruiter = recruiterRepository.save(recruiter);
            return ResponseEntity.ok(updatedRecruiter);
        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{recruiterId}/my-jobs")
    public ResponseEntity<List<Jobbb>> listJobsByRecruiter(@PathVariable Long recruiterId) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(recruiterId);

        if (recruiter.isPresent()) {
            List<Jobbb> jobs = jobService.findJobsByRecruiterId(recruiterId);
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Define LoginResponse as an inner class
    public static class LoginResponse {
        private Recruiter user;
        private String token;
    
        public LoginResponse(Recruiter user, String token) {
            this.user = user;
            this.token = token;
        }
    
        public Recruiter getUser() {
            return user;
        }
    
        public void setUser(Recruiter user) {
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
