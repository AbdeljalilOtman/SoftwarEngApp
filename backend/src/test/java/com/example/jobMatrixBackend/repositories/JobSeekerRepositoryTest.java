package com.example.jobMatrixBackend.repositories;

// src/test/java/com/example/jobMatrixBackend/repositories/JobSeekerRepositoryTest.java

import com.example.jobMatrixBackend.entities.JobSeeker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JobSeekerRepositoryTest {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Test
    @DisplayName("Should find JobSeeker by email")
    void testFindByEmail() {
        // given
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setName("Alice");
        jobSeeker.setEmail("alice@example.com");
        jobSeeker.setPassword("secret");
        jobSeekerRepository.save(jobSeeker);

        // when
        Optional<JobSeeker> found = jobSeekerRepository.findByEmail("alice@example.com");

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Alice");
    }
}
