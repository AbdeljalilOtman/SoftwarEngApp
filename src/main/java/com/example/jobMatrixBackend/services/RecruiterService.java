package com.example.jobMatrixBackend.services;

import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.repositories.RecruiterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    public Recruiter getRecruiterById(Long id) {
        return recruiterRepository.findById(id).orElse(null);
    }

    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    public Recruiter updateRecruiter(Recruiter recruiter) {
        Recruiter existingRecruiter = getRecruiterById(recruiter.getId());
        if (existingRecruiter != null) {
            existingRecruiter.setCompanyName(recruiter.getCompanyName());
            existingRecruiter.setEmail(recruiter.getEmail());
            existingRecruiter.setCompanyName(recruiter.getCompanyName());
            existingRecruiter.setRepresentativeName(recruiter.getRepresentativeName());
            return recruiterRepository.save(existingRecruiter);
        } else {
            return null;
        }
    }

    public Optional<Recruiter> getRecruitersByEmail(String email) {
        return recruiterRepository.findByEmail(email);
    }

    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }
}