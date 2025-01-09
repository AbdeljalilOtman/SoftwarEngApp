package com.example.JobMatrixBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.JobMatrixBackend.entities.Certification;
import com.example.JobMatrixBackend.services.CertificationService;

@RestController
@RequestMapping("/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping
    public ResponseEntity<Certification> addCertification(@RequestBody Certification certification) {
        return ResponseEntity.ok(certificationService.saveCertification(certification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Certification>> getCertification(@PathVariable Long id) {
        return ResponseEntity.ok(certificationService.getCertificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<Certification>> getAllCertifications() {
        return ResponseEntity.ok(certificationService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certification> updateCertification(@PathVariable Long id, @RequestBody Certification certification) throws Exception {
        return ResponseEntity.ok(certificationService.update(id, certification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}
