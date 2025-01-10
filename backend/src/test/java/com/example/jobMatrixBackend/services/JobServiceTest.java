package com.example.jobMatrixBackend.services;

// src/test/java/com/example/jobMatrixBackend/services/JobServiceTest.java


import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private Jobbb sampleJob;

    @BeforeEach
    void setUp() {
        sampleJob = new Jobbb();
        sampleJob.setId(1L);
        sampleJob.setTitle("Test Title");
        sampleJob.setDescription("Test Description");
    }

    @Test
    void testGetJobById() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(sampleJob));

        Optional<Jobbb> foundJob = jobService.getJobById(1L);

        assertThat(foundJob).isPresent();
        assertThat(foundJob.get().getTitle()).isEqualTo("Test Title");
        verify(jobRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateJob() {
        when(jobRepository.save(any(Jobbb.class))).thenReturn(sampleJob);

        Jobbb created = jobService.createJob(sampleJob);

        assertThat(created.getTitle()).isEqualTo("Test Title");
        verify(jobRepository, times(1)).save(sampleJob);
    }

    @Test
    void testUpdateJob() throws Exception {
        when(jobRepository.existsById(1L)).thenReturn(true);
        when(jobRepository.save(any(Jobbb.class))).thenReturn(sampleJob);

        sampleJob.setDescription("Updated Description");
        Jobbb updated = jobService.updateJob(1L, sampleJob);

        assertThat(updated.getDescription()).isEqualTo("Updated Description");
        verify(jobRepository, times(1)).save(sampleJob);
    }
}