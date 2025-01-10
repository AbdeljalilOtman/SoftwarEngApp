package com.example.jobMatrixBackend.services;

// src/test/java/com/example/jobMatrixBackend/services/RecruiterServiceTest.java

import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.repositories.RecruiterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RecruiterServiceTest {

    @Mock
    private RecruiterRepository recruiterRepository;

    @InjectMocks
    private RecruiterService recruiterService;

    private Recruiter sampleRecruiter;

    @BeforeEach
    void setUp() {
        sampleRecruiter = new Recruiter();
        sampleRecruiter.setId(1L);
        sampleRecruiter.setEmail("hr@example.com");
    }

    @Test
    void testCreateRecruiter() {
        when(recruiterRepository.save(any(Recruiter.class))).thenReturn(sampleRecruiter);

        Recruiter created = recruiterService.createRecruiter(sampleRecruiter);
        assertThat(created.getEmail()).isEqualTo("hr@example.com");
    }

    @Test
    void testGetRecruitersByEmail() {
        when(recruiterRepository.findByEmail("hr@example.com")).thenReturn(Optional.of(sampleRecruiter));

        Optional<Recruiter> found = recruiterService.getRecruitersByEmail("hr@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("hr@example.com");
    }
}