package com.example.jobMatrixBackend.services;


import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.entities.User;
import com.example.jobMatrixBackend.repositories.JobSeekerRepository;
import com.example.jobMatrixBackend.repositories.RecruiterRepository;
import com.example.jobMatrixBackend.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	JobSeekerRepository jobSeekerRepository;
	RecruiterRepository recruiterRepository;
	UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        JobSeekerRepository jobSeekerRepository,
        RecruiterRepository recruiterRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jobSeekerRepository = jobSeekerRepository;
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public JobSeeker signupJobSeeker(User input) {
    	
    	JobSeeker jobSeeker = new JobSeeker();

        jobSeeker.setEmail(input.getEmail());
        jobSeeker.setPassword(passwordEncoder.encode(input.getPassword()));
    
        
		return jobSeekerRepository.save(jobSeeker);

    }
    
    public Recruiter signupRecruiter(User input) {
    	
    	Recruiter recruiter = new Recruiter();
        
        recruiter.setEmail(input.getEmail());
        recruiter.setPassword(passwordEncoder.encode(input.getPassword()));

        return recruiterRepository.save(recruiter);
    }

    
    /*
    public User authenticate(User input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
		return userRepository.findByEmail(input.getEmail())
			        .orElseThrow();
    }
  */  

    
    
}
