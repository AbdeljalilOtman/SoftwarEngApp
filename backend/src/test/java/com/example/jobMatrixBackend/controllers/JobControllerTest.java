package com.example.jobMatrixBackend.controllers;
// src/test/java/com/example/jobMatrixBackend/controller/JobControllerTest.java

import com.example.jobMatrixBackend.controller.JobController;
import com.example.jobMatrixBackend.entities.Jobbb;
import com.example.jobMatrixBackend.services.JobMatchingService;
import com.example.jobMatrixBackend.services.JobService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @MockBean
    private JobMatchingService jobMatchingService;

    @Test
    void testGetAllJobs() throws Exception {
        Jobbb job1 = new Jobbb();
        job1.setId(1L);
        job1.setTitle("Software Developer");

        Jobbb job2 = new Jobbb();
        job2.setId(2L);
        job2.setTitle("Data Scientist");

        when(jobService.getAllJobs()).thenReturn(List.of(job1, job2));

        mockMvc.perform(get("/api/jobs"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.[0].title").value("Software Developer"))
               .andExpect(jsonPath("$.[1].title").value("Data Scientist"));
    }

    @Test
    void testGetJobById() throws Exception {
        Jobbb job = new Jobbb();
        job.setId(10L);
        job.setTitle("Backend Engineer");
        when(jobService.getJobById(10L)).thenReturn(Optional.of(job));

        mockMvc.perform(get("/api/jobs/10"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Backend Engineer"));
    }

    @Test
    void testUpdateJob_JobNotFound() throws Exception {
        when(jobService.getJobById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/jobs/99")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"title\":\"Updated Title\"}"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Job with ID 99 not found"));
    }

    @Test
    void testDeleteJob() throws Exception {
        when(jobService.getJobById(5L)).thenReturn(Optional.of(new Jobbb()));

        mockMvc.perform(delete("/api/jobs/5"))
               .andExpect(status().isOk())
               .andExpect(content().string("Job with ID 5 has been deleted"));

        Mockito.verify(jobService).deleteJob(5L);
    }
}
