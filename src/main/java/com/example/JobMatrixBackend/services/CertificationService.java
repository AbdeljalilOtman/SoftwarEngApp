package com.example.JobMatrixBackend.services;


import com.example.JobMatrixBackend.entities.Certification;
import com.example.JobMatrixBackend.repositories.CertificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public Certification saveCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    public List<Certification> getCertificationsByJobSeekerId(Long jobSeekerId) {
        return certificationRepository.findByJobSeekerId(jobSeekerId);
    }

    public Optional<Certification> getCertificationById(Long id) {
        return certificationRepository.findById(id);
    }
    
    public List<Certification> findAll() {
        return certificationRepository.findAll();
    }

    public Certification update(Long id, Certification certification) throws Exception {
        if (!certificationRepository.existsById(id)) {
            throw new Exception("Certification not found with id: " + id);
        }
        certification.setId(id);
        return certificationRepository.save(certification);
    }

    public void deleteCertification(Long id) {
        certificationRepository.deleteById(id);
    }
}

