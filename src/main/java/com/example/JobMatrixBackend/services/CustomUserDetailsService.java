package com.example.JobMatrixBackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.entities.Recruiter;
import com.example.JobMatrixBackend.repositories.JobSeekerRepository;
import com.example.JobMatrixBackend.repositories.RecruiterRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JobSeekerRepository jobSeekerRepository;
    private final RecruiterRepository recruiterRepository;

    public CustomUserDetailsService(JobSeekerRepository jobSeekerRepository, RecruiterRepository recruiterRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JobSeeker> jobSeeker =  jobSeekerRepository.findByEmail(username);
        if (jobSeeker.isPresent()) {
            return User.builder()
                    .username(jobSeeker.get().getEmail())
                    .password(jobSeeker.get().getPassword())
                    .roles("JOB_SEEKER")
                    .build();
        }

        Optional<Recruiter> recruiter = recruiterRepository.findByEmail(username);
        if (recruiter.isPresent()) {
            return User.builder()
                    .username(recruiter.get().getEmail())
                    .password(recruiter.get().getPassword())
                    .roles("RECRUITER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
