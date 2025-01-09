package com.example.JobMatrixBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JobMatrixBackend.entities.Application;
import com.example.JobMatrixBackend.repositories.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public Application findById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow();
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application update(Long id, Application application) throws Exception {
        if (!applicationRepository.existsById(id)) {
            throw new Exception("Application not found with id: " + id);
        }
        application.setId(id);
        return applicationRepository.save(application);
    }

    public void delete(Long id) throws Exception {
        if (!applicationRepository.existsById(id)) {
            throw new Exception("Application not found with id: " + id);
        }
        applicationRepository.deleteById(id);
    }
}
