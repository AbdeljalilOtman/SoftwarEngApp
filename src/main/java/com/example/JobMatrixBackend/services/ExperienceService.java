package com.example.JobMatrixBackend.services;

import com.example.JobMatrixBackend.entities.Experience;
import com.example.JobMatrixBackend.repositories.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public List<Experience> getExperiencesByJobSeekerId(Long jobSeekerId) {
        return experienceRepository.findByJobSeekerId(jobSeekerId);
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
}
