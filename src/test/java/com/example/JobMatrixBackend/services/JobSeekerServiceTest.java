package com.example.JobMatrixBackend.services;

import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.repositories.JobSeekerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JobSeekerServiceTest {

    @Mock
    private JobSeekerRepository jobSeekerRepository;

    @InjectMocks
    private JobSeekerService jobSeekerService;

    @Test
    public void testSaveJobSeeker() {
        JobSeeker jobSeeker = new JobSeeker("John Doe", "john.doe@example.com");

        when(jobSeekerRepository.save(jobSeeker)).thenReturn(jobSeeker);

        JobSeeker savedJobSeeker = jobSeekerService.saveJobSeeker(jobSeeker);

        assertNotNull(savedJobSeeker);
        assertEquals("John Doe", savedJobSeeker.getName());
        assertEquals("john.doe@example.com", savedJobSeeker.getEmail());
    }

    @Test
    public void testGetJobSeekerById() {
        JobSeeker jobSeeker = new JobSeeker( "John Doe", "john.doe@example.com");

        when(jobSeekerRepository.findById(1L)).thenReturn(java.util.Optional.of(jobSeeker));

        Optional<JobSeeker> foundJobSeeker = jobSeekerService.getJobSeekerById((long) 1);

        assertNotNull(foundJobSeeker);
    }
}
