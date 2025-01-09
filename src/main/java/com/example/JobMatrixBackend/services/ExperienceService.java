package com.example.JobMatrixBackend.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JobMatrixBackend.entities.Experience;
import com.example.JobMatrixBackend.repositories.ExperienceRepository;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience findById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow();
    }

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public Experience update(Long id, Experience experience) throws Exception {
        if (!experienceRepository.existsById(id)) {
            throw new Exception("Experience not found with id: " + id);
        }
        experience.setId(id);
        return experienceRepository.save(experience);
    }

    public void delete(Long id) throws Exception {
        if (!experienceRepository.existsById(id)) {
            throw new Exception("Experience not found with id: " + id);
        }
        experienceRepository.deleteById(id);
    }
}
