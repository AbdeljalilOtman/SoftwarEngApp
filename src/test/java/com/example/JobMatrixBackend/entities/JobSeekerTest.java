package com.example.JobMatrixBackend.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobSeekerTest {

    @Test
    public void testJobSeekerEntity() {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setName("John Doe");
        jobSeeker.setEmail("john.doe@example.com");

        assertNotNull(jobSeeker);
        assertEquals("John Doe", jobSeeker.getName());
        assertEquals("john.doe@example.com", jobSeeker.getEmail());
    }
}
