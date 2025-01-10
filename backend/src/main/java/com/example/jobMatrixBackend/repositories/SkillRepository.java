package com.example.jobMatrixBackend.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jobMatrixBackend.entities.Skill;


public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByNameContaining(String name);
}
