package com.example.jobMatrixBackend.controllers;

import com.example.jobMatrixBackend.controller.JobSeekerController;

// src/test/java/com/example/jobMatrixBackend/controller/JobSeekerControllerTest.java

import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.repositories.JobSeekerRepository;
import com.example.jobMatrixBackend.services.JobSeekerModel;
import com.example.jobMatrixBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobSeekerController.class)
class JobSeekerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobSeekerModel jobSeekerModel;

    @MockBean
    private JobSeekerRepository jobSeekerRepository;

    @Test
    void testLoginJobSeeker_Success() throws Exception {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(1L);
        jobSeeker.setEmail("john@example.com");
        jobSeeker.setPassword("1234");

        when(jobSeekerModel.getJobSeekersByEmail("john@example.com"))
            .thenReturn(Optional.of(jobSeeker));

        // Because we want a 200 if password matches
        mockMvc.perform(get("/api/job-seekers/login")
                .param("email", "john@example.com")
                .param("password", "1234"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.user.email").value("john@example.com"))
            .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testLoginJobSeeker_Unauthorized() throws Exception {
        when(jobSeekerModel.getJobSeekersByEmail("nope@example.com"))
            .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/job-seekers/login")
                .param("email", "nope@example.com")
                .param("password", "whatever"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void testRegisterJobSeeker() throws Exception {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setName("Alice");
        jobSeeker.setEmail("alice@example.com");
        jobSeeker.setPassword("secret");

        when(jobSeekerRepository.save(any(JobSeeker.class))).thenReturn(jobSeeker);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jobSeeker);

        mockMvc.perform(post("/api/job-seekers/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.user.email").value("alice@example.com"))
            .andExpect(jsonPath("$.token").exists());
    }
}