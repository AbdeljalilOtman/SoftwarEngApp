package com.example.JobMatrixBackend.controllers;

import com.example.JobMatrixBackend.entities.Certification;
import com.example.JobMatrixBackend.services.CertificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CertificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CertificationService certificationService;

    @InjectMocks
    private CertificationController certificationController;

    private Certification certification;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(certificationController).build();

        certification = new Certification();
        certification.setTitle("Java Certification");
        certification.setIssuingOrganization("Oracle");
        certification.setCertificateUrl("http://example.com/cert");
    }

    @Test
    public void testCreateCertification() throws Exception {
        when(certificationService.saveCertification(certification)).thenReturn(certification);

        mockMvc.perform(post("/certifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Java Certification\", \"issuingOrganization\":\"Oracle\", \"certificateUrl\":\"http://example.com/cert\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Java Certification"));

        verify(certificationService, times(1)).saveCertification(certification);
    }

    @Test
    public void testGetAllCertifications() throws Exception {
        mockMvc.perform(get("/certifications"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(certificationService, times(1)).findAll();
    }

    @Test
    public void testGetCertificationById() throws Exception {
        when(certificationService.getCertificationById(certification.getId())).thenReturn(java.util.Optional.of(certification));

        mockMvc.perform(get("/certifications/{id}", certification.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Certification"));

        verify(certificationService, times(1)).getCertificationById(certification.getId());
    }

    

	
}
