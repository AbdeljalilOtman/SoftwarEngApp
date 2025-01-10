package com.example.jobMatrixBackend.services;

// src/test/java/com/example/jobMatrixBackend/services/JobMatchingServiceTest.java

import com.example.jobMatrixBackend.entities.Experience;
import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.entities.Skill;
import com.example.jobMatrixBackend.repositories.JobRepository;
import com.example.jobMatrixBackend.repositories.JobSeekerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JobMatchingServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobSeekerRepository jobSeekerRepository;

    @InjectMocks
    private JobMatchingService jobMatchingService;

    private Jobbb testJob;
    private JobSeeker testJobSeeker;

    @BeforeEach
    void setUp() {
        // Create a Job
        testJob = new Jobbb();
        testJob.setId(1L);
        testJob.setExperienceRequired(2);

        Skill skillJava = new Skill();
        skillJava.setId(100L);
        skillJava.setName("Java");
        testJob.getSkillsRequired().add(skillJava);

        // Create a JobSeeker
        testJobSeeker = new JobSeeker();
        testJobSeeker.setId(10L);

        // Add experiences that sum up 2 years
        Experience exp = new Experience();
        exp.setStartDate(LocalDate.of(2020, 1, 1));
        exp.setEndDate(LocalDate.of(2022, 1, 1));
        exp.setPosition("Software Engineer");
        testJobSeeker.getExperiences().add(exp);

        // Add skill "Java" to JobSeeker
        testJobSeeker.getSkills().add(skillJava);
    }

    @Test
    void testMatchJobSeekersForJob() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(testJob));
        when(jobSeekerRepository.findAll()).thenReturn(List.of(testJobSeeker));

        // Call the matching method
        List<JobSeeker> matched = jobMatchingService.matchJobSeekersForJob(1L);

        // We expect the testJobSeeker to match
        assertThat(matched).hasSize(1);
        assertThat(matched.get(0).getId()).isEqualTo(10L);
    }
}
