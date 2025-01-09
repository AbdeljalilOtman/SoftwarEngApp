package com.example.JobMatrixBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.JobMatrixBackend.entities.Experience;
import com.example.JobMatrixBackend.services.ExperienceService;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<Experience> addExperience(@RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.save(experience));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperience(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience experience) throws Exception {
        return ResponseEntity.ok(experienceService.update(id, experience));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) throws Exception {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
