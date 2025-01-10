package com.example.JobMatrixBackend.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JobMatrixBackend.Response.LoginResponse;
import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.entities.Recruiter;
import com.example.JobMatrixBackend.entities.User;
import com.example.JobMatrixBackend.services.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup/recruiter")
    public ResponseEntity<Recruiter> registerRecruiter(@RequestBody User user) {
    	Recruiter registeredUser = authenticationService.signupRecruiter(user);

        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/signup/jobSeeker")
    public ResponseEntity<JobSeeker> registerJobSeeker(@RequestBody User user) {
    	JobSeeker registeredUser = authenticationService.signupJobSeeker(user);

        return ResponseEntity.ok(registeredUser);
    }
    
    
/*
    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestBody User loginUser) {
        User authenticatedRecruiter = authenticationService.authenticate(loginUser);
        return ResponseEntity.ok(loginUser);
    }
 */  
    
}