package com.example.jobMatrixBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobMatrixBackend.entities.Skill;
import com.example.jobMatrixBackend.repositories.SkillRepository;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:8081")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Skill>> searchSkillsByName(@RequestParam String name) {
        List<Skill> skills = skillRepository.findByNameContaining(name);
        return ResponseEntity.ok(skills);
    }

    @PostMapping
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill) {
        Skill savedSkill = skillRepository.save(skill);
        return ResponseEntity.ok(savedSkill);
    }
}
