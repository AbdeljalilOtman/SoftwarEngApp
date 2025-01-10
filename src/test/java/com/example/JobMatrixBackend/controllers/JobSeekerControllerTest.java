package com.example.JobMatrixBackend.controllers;

import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.controllers.JobSeekerController;
import com.example.JobMatrixBackend.services.JobSeekerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

public class JobSeekerControllerTest {

    @Mock
    private JobSeekerService jobSeekerService;

    @InjectMocks
    private JobSeekerController jobSeekerController;

    private MockMvc mockMvc;

    public JobSeekerControllerTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobSeekerController).build();
    }

    @Test
    public void testCreateJobSeeker() throws Exception {
        JobSeeker jobSeeker = new JobSeeker("John Doe", "john.doe@example.com");

        when(jobSeekerService.saveJobSeeker(jobSeeker)).thenReturn(jobSeeker);

        mockMvc.perform(post("/jobseekers")
                .contentType("application/json")
                .content("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetJobSeeker() throws Exception {
        Optional<JobSeeker> jobSeeker = Optional.ofNullable(new JobSeeker("John Doe", "john.doe@example.com"));

        when(jobSeekerService.getJobSeekerById((long) 1)).thenReturn(jobSeeker);

        mockMvc.perform(get("/jobseekers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
